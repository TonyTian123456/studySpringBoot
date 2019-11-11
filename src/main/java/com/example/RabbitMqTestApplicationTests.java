/*
package com.example;

import com.example.domain.delay.Booking;
import com.example.rabbirMq.ImmediateSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTestApplicationTests {

	@Autowired
	ImmediateSender immediateSender;
	
	@Test
	public void test() {
	    Booking booking = new Booking();
        booking.setBookingContent("hhaha");
        booking.setBookingName("预定房子");
        booking.setBookingTime(new Date());
        booking.setOperatorName("hellen");
	    immediateSender.send(booking, 1000);
	}
}
*/
