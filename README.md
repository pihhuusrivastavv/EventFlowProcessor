
# 📌 Event Flow Processing System

## 📖 Overview  
A **robust, multi-threaded event processing system** built in Java using the **producer-consumer architecture**, focusing on **fault tolerance, reliability, and data consistency**.

## ⚙️ Features  
- Concurrent producer-consumer model  
- Priority-based events (`INFO`, `WARNING`, `ERROR`)  
- Persistent storage for durability  
- Retry mechanism (max 3 attempts)  
- Dead Letter Queue (DLQ)  
- Event confirmation (idempotency)  
- Crash recovery support  

## 🔄 Workflow  
- Producer → creates & stores events  
- Queue → synchronized buffer (capacity = 5)  
- Consumer → processes with retries  
- Success → stored + confirmed  
- Failure → moved to DLQ  
- Recovery → reloads unprocessed events  

## 📁 Files Generated  
- `events_info`  
- `events.info`  
- `Dead.events.log`  

## 🚀 Execution  
```bash
javac */*.java
java MainProgram.EventFlowApplication
```

## 🧠 Concepts  
Multithreading • Synchronization • Fault Tolerance • Idempotency • Event-driven Design
