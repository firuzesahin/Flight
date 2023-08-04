package com.turkcell.airlineflights.business.rules;

import com.turkcell.airlineflights.business.dto.requests.create.CreateFlightRequest;
import com.turkcell.airlineflights.entities.Flight;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FlightBusinessRules
{
    private static SessionFactory sessionFactory;
    //Hibernate SessionFactory, veritabanı bağlantısı yapılır.

    static
    {
        try
        {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            //Yapılandırılması ve oluşturulmasıdır.
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean canAddNewFlight(CreateFlightRequest createFlightRequest) // Yeni bir uçuş eklenip eklenemeyeceğini kontrol eder.
    {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean canAdd = false;

        try
        {
            tx = session.beginTransaction();

            // Kalkış ve varış havaalanlarını al
            int airportSID = createFlightRequest.getAirportS().getId();
            int airportEID = createFlightRequest.getAirportE().getId();

            // Uçuş sayısı kontrolünü yap
            String sql = "SELECT COUNT(*) FROM flight WHERE airportS_id = :departureAirportID AND airportE_id = :arrivalAirportID AND start_date BETWEEN :startOfDay AND :endOfDay";
            NativeQuery<?> query = session.createNativeQuery(sql);
            query.setParameter("departureAirportID", airportSID);
            query.setParameter("arrivalAirportID", airportEID);
            query.setParameter("startOfDay", createFlightRequest.getStartDate().withHour(0).withMinute(0).withSecond(0));
            query.setParameter("endOfDay", createFlightRequest.getStartDate().withHour(23).withMinute(59).withSecond(59));


            Number flightCount = (Number) query.uniqueResult();
            canAdd = flightCount.intValue() < 3;

            tx.commit();
        }
        catch (Exception e)
        {
            if (tx != null)
            {
                tx.rollback();
            }
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }

        return canAdd;
    }

    public void addFlight(CreateFlightRequest createFlightRequest)
    {
        if (canAddNewFlight(createFlightRequest)) {
            Session session = sessionFactory.openSession();
            Transaction tx = null;

            try
            {
                tx = session.beginTransaction();

                // CreateFlightRequest nesnesini Flight nesnesine dönüştür.
                Flight flight = createFlightRequest.toFlightEntity();

                /*
                Airline airline = flight.getAirline();
                airline.incrementNft();
                 */

                // Uçuşu veritabanına ekler.
                session.save(flight);

                tx.commit();
            }
            catch (Exception e)
            {
                if (tx != null)
                {
                    tx.rollback();
                }
                e.printStackTrace();
            }
            finally
            {
                session.close();
            }
        }
        else
        {
            throw new IllegalArgumentException("Uçuş eklenemedi: Uçuş sayısı sınırlamayı aşıyor.");
        }
    }
}