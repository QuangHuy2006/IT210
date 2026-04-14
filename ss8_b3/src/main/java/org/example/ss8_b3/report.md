# input/output

```text
Input: double money
Output: true/false
```
# pseudocode
```text
function isValid(withdrawAmount):
    if withdrawAmount == null:
        return false

    if withdrawAmount < 0:
        return false

    if withdrawAmount < 50000:
        return false

    if withdrawAmount % 10000 != 0:
        return false

    return true
```

# interface custom annotation
```java
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MultipleOfTenThousandValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipleOfTenThousand {
    String message() default "Số tiền rút phải >= 50,000 và là bội số của 10,000";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

```

# custom Annotation
```java
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MultipleOfTenThousandValidator implements ConstraintValidator<MultipleOfTenThousand, Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (value < 50000) {
            return false; 
        }
        return value % 10000 == 0; 
    }
}
```


# DTO
```java
public class WithdrawRequestDto {

    @MultipleOfTenThousand
    private Long withdrawAmount;

    // getters, setters
}

```