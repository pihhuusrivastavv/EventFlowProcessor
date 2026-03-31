**📌 Event Flow Processing System**


**📖 Overview**

A robust, multi-threaded event processing system built in Java, implementing the producer-consumer architecture with a strong focus on reliability, fault tolerance, and data consistency.
The system incorporates persistent storage, controlled retry mechanisms, and dead-letter queue handling to simulate real-world distributed event processing workflows.

**⚙️ Features**

**1) Concurrent producer-**consumer model with synchronized shared queue
**2) Priority-driven event** classification (INFO, WARNING, ERROR)
**3) Durable event persistence** to prevent data loss
**4) Controlled retry strategy** (up to 3 attempts per event)
**5) Dead Letter Queue (DLQ)** for handling irrecoverable failures
**5) Idempotent processing** via event confirmation tracking
**6) Crash recovery mechanism** to reload unprocessed events


**🔄 Workflow**

**1) Producer** → Generates events, persists them, and publishes to queue
**2) Queue** → Acts as a bounded, thread-safe buffer (capacity = 5)
**3) Consumer** → Processes events with retry and failure handling logic
**4) Success Path** → Event is confirmed and permanently stored
**5) Failure Path** → Event is redirected to Dead Letter Queue
**6) Recovery** → System reloads and reprocesses unconfirmed events on restart


**📁 Generated Files**

**1) events_info** → Persistent event log (for recovery)
**2) events.info** → Successfully processed events
**3) Dead.events.log** → Failed events after retries (DLQ)


**🚀 Execution**

javac */*.java
java MainProgram.EventFlowApplication


**🧠 Core Concepts**

**• Multithreading
• Synchronization
• Fault Tolerance
• Retry Strategies 
• File-based Persistence 
• Idempotency
• Event-driven Design**
