CREATE TABLE IF NOT EXISTS payment_instruction (
      id TEXT NOT NULL,
      source_system TEXT NOT NULL,
      payer_account TEXT NOT NULL,
      payee_account TEXT NOT NULL,
      amount DECIMAL(19, 4) NOT NULL,
      currency TEXT NOT NULL CHECK (length(currency) = 3),
      requested_execution_date DATE NOT NULL,
      status TEXT NOT NULL CHECK (status IN ('PENDING', 'VALIDATION_FAILED', 'PROCESSING', 'COMPLETED', 'REJECTED', 'FAILED', 'CANCELLED')),
      requested_rail TEXT NOT NULL,
      created_at DATE NOT NULL,
      updated_at DATE NOT NULL,
      PRIMARY KEY (id, source_system)
    );