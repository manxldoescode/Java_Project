# # Leaky Bucket Rate Limiter (Java)

## This project implements a simple Leaky Bucket algorithm for rate limiting in Java.
## The algorithm controls incoming requests by treating them as “water” flowing into a bucket:

The Leaky Bucket Rate Limiter is a simple and effective algorithm used to control the flow of incoming requests by processing them at a steady, predictable rate. It works by simulating a bucket that gradually leaks water (requests) over time, allowing new requests only when there is available capacity. This prevents sudden spikes in traffic and ensures smoother, more controlled handling of requests, making it ideal for APIs, backend services, and traffic management systems.


* The bucket has a maximum capacity (threshold).

* Water leaks out at a fixed rate (1 request per second).

* If the bucket is full, new requests are rejected.

* If there is space, requests are accepted and added to the bucket.

* This helps smooth out bursts and ensures a stable rate of request processing.

* Key Features

* Thread-safe implementation using AtomicLong

* Time-based leak calculation

* Simple tryAcquire() method to check if a request is allowed

* Example Main class included for quick testing

## Example

The sample Main program sends multiple requests and demonstrates how the limiter allows or rejects them based on current bucket capacity.
