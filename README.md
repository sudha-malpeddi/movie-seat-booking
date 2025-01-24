# movie-seat-booking
A Spring Boot application for concurrent movie seat booking using optimistic and pessimistic locking.

**1. Optimistic Locking**
**Concept**: Assumes conflicts are rare. Rather than locking the resource preemptively, it allows multiple transactions to proceed. At the time of updating the resource, it checks if any other transaction has modified it in the meantime.

**Implementation**: Uses a version or timestamp column to detect conflicts. If a conflict is detected (i.e., the version/timestamp has changed), the transaction is retried or aborted.
**Advantages**:
Better performance in scenarios with low contention.
No database-level locks are held, reducing potential for deadlocks.
**Use Case**:
Best for read-heavy systems or environments with low contention.
**Example**: Booking systems where most attempts to book a resource are unique.

**2. Pessimistic Locking**
**Concept**: Assumes conflicts are likely. Locks the resource as soon as it is accessed, preventing other transactions from making changes until the current transaction completes.
**Implementation**: Uses database-level locks (e.g., SELECT FOR UPDATE) to block other transactions from accessing the resource until the lock is released.
**Advantages**:
Guarantees consistency in high-contention scenarios.
Prevents lost updates or dirty reads.
**Disadvantages**:
Potential performance issues due to locking.
Increased likelihood of deadlocks.
**Use Case**:
Best for write-heavy systems or environments with high contention.
**Example**: Real-time trading systems or high-contention booking systems.
