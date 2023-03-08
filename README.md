<h1 align="center"> Microservices App Examples</h1>
<br />
<h3 align="center">
<p> This an example of microservice application with Java Springboot, ApiGateway, Eureka-Server, Rabbitmq. </p>
  <p>This example has creation of customer(microservice), make a request to fraud(microservice). If its ok, save in database.</p>
  <p>When register, the microservice Notification send a email using queue with rabbitmq.</p>

<br/>
<h4> 
You need start in order:
<ol>
<li>eureka-server. </li>
<li>apiGateway </li>
<li>customer </li>
<li>fraud </li>
<li>notification </li>

</ol>
</h4>

### Commands
```bash
# Clone
$ git clone https://github.com/mariuo/exampleMicroservice

# Run docker-composegit s
$ docker-compose up -d

# Command to down all containers
$ docker-compose down

# Command to down and remove all containers/images
$ docker-compose down --volumes --rmi all

```
### http://localhost:5050/ - pgAdmin (admin@admin.com - root)
### http://localhost:9411/ - eureka-server

### http://localhost:8000/api/v1/customers - post
### http://localhost:8082/api/v1/notification - post

#### references:
 https://www.youtube.com/watch?v=p485kUNpPvE&list=PLwvrYc43l1Mwqpf9i-1B1gXfMeHOm6DeY&ab_channel=Amigoscode