package com.payment.instruction.process.validator;

import com.payment.instruction.process.domain.dto.CreateInstructionRequest;
import com.payment.instruction.process.domain.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class InstructionIntakeValidator {

    // Keep these small and explicit; expand via config later.
    private static final Pattern ISO_CURRENCY = Pattern.compile("^[A-Z]{3}$");

    // Example allowed rails + initial statuses (make configurable per tenant/source later).
    private static final Set<String> ALLOWED_RAILS = Set.of("FAST", "PAYNOW", "SWIFT", "ACH", "INTERNAL");


    public void validate(CreateInstructionRequest input) {
        // 1) Required fields (fail fast)
        requireNotBlank(input.getId(), "id");
        requireNotBlank(input.getSourceSystem(), "sourceSystem");
        requireNotBlank(input.getPayerAccount(), "payerAccount");
        requireNotBlank(input.getPayeeAccount(), "payeeAccount");
        requireNotBlank(input.getCurrency(), "currency");
        requireNotBlank(input.getRequestedRail(), "rail");

        if (input.getAmount() == null) throw new ValidationException("amount", "must be provided");
        if (input.getRequestedExecutionDate() == null) throw new ValidationException("requestedExecutionDate", "must be provided");

        // 2) Format + normalization checks
        String currency = input.getCurrency().trim().toUpperCase();
        if (!ISO_CURRENCY.matcher(currency).matches()) {
            throw new ValidationException("currency", "must be ISO 4217 uppercase 3-letter code (e.g. SGD)");
        }

        String rail = input.getRequestedRail().trim().toUpperCase();
        if (!ALLOWED_RAILS.contains(rail)) {
            throw new ValidationException("rail", "unsupported rail: " + rail);
        }

        // 3) Amount rules
        if (input.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValidationException("amount", "must be > 0");
        }
        // Optional: enforce scale (e.g., 2 dp) per currency later; keep minimal here.

        // 4) Account sanity rules
        if (input.getPayerAccount().trim().equals(input.getPayeeAccount().trim())) {
            throw new ValidationException("payeeAccount", "must be different from payerAccount");
        }
    }

    private static void requireNotBlank(String v, String field) {
        if (v == null || v.trim().isEmpty()) throw new ValidationException(field, "must be provided");
    }
}