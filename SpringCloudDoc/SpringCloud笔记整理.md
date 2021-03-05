# 		SpringCloud笔记整理

### Study Plan

开始学习时间：2021年2月18日

学习的计划周期：1月

学习的目标：了解什么是微服务【优势和不足】，微服务的常用组件，微服务环境搭建。

### 微服务基础

#### 什么是微服务架构？

简单的说，微服务是系统架构上的一种设计风格，它的主旨是将一个原本独立的系统拆分成多个小型服务，这些小型服务在各自独立的进程中运行，服务之间通过http的restful API进行通信协作，被拆分的小型服务都围绕着系统中某一项或者一些耦合度较高的业务功能进行构建，并且每个服务维护这自身的数据存储、业务开发、自动化测试案例、以及独立部署机制，由于有了轻量级的通信基础，所以这些微服务可以使用不同的语言来编写。

#### 微服务架构引起的问题

**运维的新挑战** 在微服务架构中，运维人员需要管理的进程数量会大大增加，有条不紊的将这些进程编排和组织起来并不是一件容易的事。

**接口的一致性** 虽然我们拆分了服务，但是业务逻辑上的依赖并不会消除，只是从单体应用中的代码依赖转变成服务间的通信依赖。而当我们对原有接口进行了修改调整，那么交互方也要进行这样的协调。因此，我们需要更加完善的接口和版本管理，或严格的遵循版本管理。	

**分布式的复杂性** 由于拆分之后的各个微服务都在自己独立的进程中运行，他们只能通过通信来进行协作，所以分布式环境问题通常是微服务架构设计的要考虑的重要因素，网络延迟、**分布式事务**、异步消息等。

### 与单体系统的区别

在传统的企业系统架构中，我们针对一个复杂的业务需求通常使用对象或者业务类型来构建一个单体项目。在项目中我们通常将需求分为三个主要部分，数据库->服务端处理->前端展现。在业务发展的初期，由于所有的业务逻辑在一个应用中，开发，测试，部署都还比较容易且方便。但是，随着企业的发展，系统为了应对不同的业务需求会不断的为该单体项目增加不同的业务模块，而且由于移动端设备的发展，前端展现的模块已经不仅仅局限于web，这对于系统后端向前端的支持需要更多的接口模块。单体应用由于面对的业务需求更为宽泛，不断扩大的需求会使得单体应用变得越来越臃肿。单体应用的问题就会逐渐凸显出来，由于单体系统是部署在一个进程内，往往我们修改了一个很小的功能。为了部署上线会影响其他功能的运行。并且，单体应用中的这些模块的使用场景、并发量、消耗的资源类型都各不相同，对于资源的利用又互相影响。这使得很难评估各个业务模块的系统容量，所以单体系统在初期虽然可以方便的进行开发和使用，但是随着系统的发展，维护成本会变得越来越大，且难以控制。

为了解决单体系统变得庞大臃肿之后产生的难以维护的问题，微服务架构诞生并为大家所关注。我们将系统中的不同模块拆分成多个不同的服务，这些服务都能够独立的部署和扩展。由于每个服务都运行在自己的进程内，在部署上有稳固的边界，这样每个服务的更新都不会影响其他服务的运行。同时由于独立部署，我们可以更加准确的为每个服务评估性能容量，通过配合服务间的协作流程也可以更容易的发现系统的瓶颈位置。

### 微服务概念总结

单体系统的优势：需求通常分为三个部分：数据库、服务端处理、前端展现，因此开发、测试、部署比较容易

单体系统的劣势：随着系统的发展，系统后端向前端支持的接口越来越多，不断扩大的需求使得单体应用变得越来越臃肿，而且单体应用是部署在一个进程内，我们很难评估各个模块的使用场景、并发量，消耗的资源都各不相同。进一步会导致维护成本越来越大。



微服务的诞生：是为了解决单体系统变得庞大臃肿之后产生的难以维护的问题。

微服务的优势：将一个独立的系统拆分成多个小型服务。这些服务都各自运行在自己独立的进程上。服务之间通过http 的Restful API 通信协作。每个服务都维护自身的数据存储、业务开发、自动化测试、独立部署机制。并且因为有了轻量级的通信基础，各个服务可以采用不同的语言进行开发。

微服务的劣势：



### 服务注册与发现

#### 服务治理

SpringCloud 封装了NetFlix公司开发的Eureka模块来实现服务治理。

什么是服务治理：在传统的RPC远程调用框架中，管理服务与服务之间的依赖关系比较复杂，所以需要使用服务治理，管理服务之间的依赖关系，可以实现服务调用，负载均衡、容错等，可以实现服务发现与容错。

**Warning：** **什么是传统的RPC【Remote Procedure Call 】远程调用？**

**Answer:**rpc指的是在计算机A上的进程，调用另外一台计算机B的进程，A上的进程被挂起，B上的被调用进程开始执行后，产生返回值给A，A继续执行。

调用方可以通过参数将信息传递给被调用方，而后通过返回结果得到信息，这个过程对于开发人员来说是透明的如同厨师一样，服务员把菜单给后厨，厨师告诉洗菜人，备菜人，开始工作，完成工作后，整个过程对于服务员是透明的，他完全不用管后厨是怎么把菜做好的。

由于服务在不同的机器上，远程调用必经网络通信，调用服务必须写一坨网络通信代码，很容易出错且很复杂，因此就出现了RPC框架。如Alibaba的Dubbo

### EUREKA服务注册与发现

#### **什么是服务注册与发现？**

Eureka采用了CS设计架构，Eureka Server 作为服务注册功能的服务器，它是服务注册中心。而系统中的其他微服务，使用Eureka 的客户端连接到Eureka Server 并维持心跳连接，这样系统的维护人员就可以通过Eureka Server来监控系统中的各个微服务是否正常运行。

在服务注册与发现中，有一个注册中心。当服务器启动的时候，会把当前自己服务器的信息比如服务器地址通讯地址别名方式注册到注册中心上。另一方（消费者|服务提供者），用别名的方式去注册中心上获取实际的服务通讯地址

![1614836292222](D:\IdeaProject\cloud2020\SpringCloudDoc\SpringCloud笔记整理.assets\1614836292222.png)

![1614836307463](D:\IdeaProject\cloud2020\SpringCloudDoc\SpringCloud笔记整理.assets\1614836307463.png)

#### **Eureka的两大组件**

![1614841647637](D:\IdeaProject\cloud2020\SpringCloudDoc\SpringCloud笔记整理.assets\1614841647637.png)

**服务发现**

对于注册进Eureka里面的微服务，可以通过服务发现来获得该服务的信息。

方式：主启动类上面添加@EnableDiscoveryClient

```java
 @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        
        for (String element : services) {
            log.info("***** element:"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
```



#### **Eureka的自我保护**

![1614843615462](D:\IdeaProject\cloud2020\SpringCloudDoc\SpringCloud笔记整理.assets\1614843615462.png)

#### **Eureka自我保护产生的原因**

![1614843658582](D:\IdeaProject\cloud2020\SpringCloudDoc\SpringCloud笔记整理.assets\1614843658582.png)

![1614843663154](D:\IdeaProject\cloud2020\SpringCloudDoc\SpringCloud笔记整理.assets\1614843663154.png)



### Zookeeper服务注册与发现

Zookeeper是一个分布式协调工具，可以实现注册中心的功能。

Zk服务器替代Eureka服务器，zk作为服务器的注册中心

zk是c/s架构，但是没有可视化的页面



### Consul服务注册与发现

没有深入了解。。。

### Ribbon负载均衡和服务调用

#### 什么是负载均衡？什么是服务调用？

简单的说就是将用户的请求平摊的分配到多个服务上，从而达到系统的HA(高可用)。常见的负载均衡软件有Nginx,LVS，硬件等。

#### 什么是Ribbon

![1614911290006](D:\IdeaProject\cloud2020\SpringCloudDoc\SpringCloud笔记整理.assets\1614911290006.png)

#### **Ribbon本地负载均衡客户端VS Nginx服务端负载均衡区别**

1. Nginx是服务器负载均衡，客户端的所有请求都会交给nginx，然后由nginx实现转发请求。即负载均衡是由服务端实现的。
2. Ribbon本地负载均衡，在调用微服务接口的时候，会在注册中心上获取注册信息服务列表之后缓存到JVM本地，从而在本地实现RPC远程服务调用技术

![1614910980491](D:\IdeaProject\cloud2020\SpringCloudDoc\SpringCloud笔记整理.assets\1614910980491.png)

#### Ribbon在客户端实现负载均衡的原理

后续研究。。。

#### **Ribbon的简单使用**

在配置文件中列出LoadBalancer（简称LB）后面的所有机器

```java
@SpringBootApplication
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyselfRule.class)
public class Order80ApplicationRibbon {
    public static void main(String[] args) {
        SpringApplication.run(Order80ApplicationRibbon.class);
    }
}
```

配置自定义负载均衡的规则

```java
@Configuration
public class MyselfRule {
    @Bean
    public IRule iRule(){
        //return new RandomRule(); LB策略 随机数法
        //return new RoundRobinRule(); 轮询
        return new WeightedResponseTimeRule();
    }
}
```

controller调用服务

```java
@RestController
@Slf4j
public class OrderController {
    private static final String URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        //去调用Payment的服务接口
        log.info("调用支付服务的接口");
        return restTemplate.postForObject(URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> getById(@PathVariable("id")Integer id){
        log.info("调用支付服务的接口，查询订单");
        return restTemplate.getForObject(URL+"/payment/"+id,CommonResult.class);
    }
}
```



客户端的配置:

```yaml
server:
  port: 80

eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/
  instance:
    instance-id: order80
    prefer-ip-address: true
spring:
  application:
    name: order80
```

### OpenFeign服务接口调用

#### 什么是Feign？

Feign是一个声明性的Web服务客户端。它使编写Web服务客户端变得更容易。要使用Feign，请创建一个界面并对其进行注释。它具有可**插入**的注释支持，包括Feign注释和JAX-RS注释。Feign还支持**可插拔编码器**和**解码器**。Spring Cloud增加了对Spring MVC注释的支持，并使用了HttpMessageConvertersSpring Web中默认使用的注释。Spring Cloud集成了Ribbon和Eureka，在使用Feign时提供负载均衡的http客户端

Feign，假装、伪装。OpenFeign可以使消费者将提供者提供的服务名伪装为接口进行消费，消费者只需使用“Service接口 + 注解”的方式即可直接调用Service接口方法，**而无需再使用RestTemplate**了


#### OpenFeign有什么优势？

![1614912524327](D:\IdeaProject\cloud2020\SpringCloudDoc\SpringCloud笔记整理.assets\1614912524327.png)

#### **Feign和OpenFeign的区别**？

![1614912574046](D:\IdeaProject\cloud2020\SpringCloudDoc\SpringCloud笔记整理.assets\1614912574046.png)

#### OpenFeign简单使用

1. 主启动类需要添加@EnableFeignClients注解

   ```java
   @SpringBootApplication
   @EnableFeignClients
   public class OrderConsumer80 {
       public static void main(String[] args) {
           SpringApplication.run(OrderConsumer80.class,args);
       }
   }
   ```

   

2. 业务逻辑接口+@FeignClient配置调用provider服务

   ```java
   @Component
   @FeignClient(name = "CLOUD-PAYMENT-SERVICE")
   public interface PaymentFeignService {
   
       @GetMapping("/payment/{id}")
       public CommonResult<Payment> findById(@PathVariable("id") Integer id);
   }
   ```

   

3. 控制层Controller

   ```java
   @RestController
   @Slf4j
   public class OrderController {
   
       private static final String URL = "CLOUD-PAYMENT-SERVICE";
       @Autowired
       private PaymentFeignService paymentFeignService;
   
       @GetMapping("/consumer/payment/{id}")
       public CommonResult<Payment> payment(@PathVariable("id") Integer id){
           log.info("查询订单接口");
           return  paymentFeignService.findById(id);
       }
   
       @GetMapping("/test/dev")
       String test(){
           return "devtools";
       }
   
   }
   ```

   