package com.amigoscode.customer;

import com.amigoscode.clients.fraud.FraudCheckResponse;
import com.amigoscode.clients.fraud.FraudClient;
import com.amigoscode.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.amigoscode.rabbitmq.RabbitMQMessageProducer;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
//    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
//    private final NotificationClient notificationClient;

    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        customerRepository.saveAndFlush(customer);

//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject("http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId());
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("Fraudster");
        }
        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s welcome to Microservices....", customer.getFirstName())

        );

//        notificationClient.sendNotification(
//                new NotificationRequest(
//                        customer.getId(),
//                        customer.getEmail(),
//                        String.format("Hi %s welcome to Microservices....", customer.getFirstName())
//                )
//        );
        rabbitMQMessageProducer.publish(
                notificationRequest, "internal.exchange", "internal.notification.routing-key"
        );

    }
}
