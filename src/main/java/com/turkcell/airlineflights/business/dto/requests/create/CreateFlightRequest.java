package com.turkcell.airlineflights.business.dto.requests.create;

import com.turkcell.airlineflights.entities.Flight;
import com.turkcell.airlineflights.entities.Airline;
import com.turkcell.airlineflights.entities.Airport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFlightRequest
{
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Airline airline;
    private Airport airportS; //Kalkış havaalanı.
    private Airport airportE; //Varış havaalanı.

    private static SessionFactory sessionFactory;

    static
    {
        try
        {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            //Yalnızca bir kez çalışır.
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //Hata durumunda istisnaları yakalar ve ekrana yazdırır.
        }
    }
    private int getFlightCountBetweenAirports(int departureAirportID, int arrivalAirportID)
    {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        int flightCount = 0;

        try
        {
            tx = session.beginTransaction();

            String sql = "SELECT COUNT(*) FROM Flight WHERE airportS_id = :departureAirportID AND airportE_id = :arrivalAirportID";
            NativeQuery<?> query = session.createNativeQuery(sql);
            query.setParameter("departureAirportID", departureAirportID);
            query.setParameter("arrivalAirportID", arrivalAirportID);

            Number result = (Number) query.uniqueResult();
            flightCount = result.intValue();

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

        return flightCount;
    }

    public boolean isFlightCountAllowed(int maxFlightCount)
    {
        //Uçuş sayısı 3e gelmediyse true, aşılmışsa false döner.
        int flightCount = getFlightCountBetweenAirports(airportS.getId(), airportE.getId());
        return flightCount < maxFlightCount;
    }

    public Flight toFlightEntity()
    {
        if (isFlightCountAllowed(3))
        {
            Flight flight = new Flight();
            flight.setStartDate(startDate);
            flight.setEndDate(endDate);
            flight.setAirline(airline);
            flight.setAirportS(airportS);
            flight.setAirportE(airportE);

            return flight;
        }
        else
        {
            throw new IllegalArgumentException("Uçuş sayısı sınırlamayı aşıyor.");
        }
    }
}