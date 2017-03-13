# Spring Batch Step간 데이터 공유

## 참고 문서

* http://docs.spring.io/spring-batch/trunk/reference/html/patterns.html#passingDataToFutureSteps

* http://stackoverflow.com/questions/32736377/spring-batch-pass-data-between-reader-and-writer

* http://stackoverflow.com/questions/21241683/spring-batch-beforestep-does-not-work-with-stepscope


## 방법

*  ExecutionContextPromotionListener 객체에 사용한 키 설정

```java

@Bean
public ExecutionContextPromotionListener promotionListener() {
    ExecutionContextPromotionListener executionContextPromotionListener = new ExecutionContextPromotionListener();
    executionContextPromotionListener.setKeys(new String[]{"step2"});

    return executionContextPromotionListener;
}

```

* step에 리스터 등록

```java

@Bean
public Step step1 () {
    return stepBuilderFactory.get("step1")
            .<String, String>chunk(1)
            .reader(step1Reader())
            .processor(step1Processor())
            .writer(step1Writer())
            .listener(promotionListener())
            .build();
}

```

* @BeforeStep 이용해서 key에 value 세팅 (@StepScope 설정해야 동작)

```java

@Component
@StepScope
public class Step1Writer implements ItemWriter<String> {

    private StepExecution stepExecution;

    @Override
    public void write(List<? extends String> items) throws Exception {
        System.out.println("Items from processor : " + items.toString());

        ExecutionContext stepContext = this.stepExecution.getExecutionContext();
        stepContext.put("step2", "Pass To Next Step");
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }
}


```

* 사용하고자하는 다음 step 에서 꺼내서 사용
 
```java

@Component
@StepScope
public class Step2Processor implements ItemProcessor<String, String> {
    private Object someObject;

    @Override
    public String process(String item) throws Exception {

        System.out.println(someObject + " " + item);
        return null;
    }

    @BeforeStep
    public void retrieveInterstepData(StepExecution stepExecution) {
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();
        this.someObject = jobContext.get("step2");

    }
}

```