
# 🖥️ System Monitor API

A **Spring Boot API** for monitoring system resources such as **CPU, memory, disk, network, processes, and sensors**.
The API exposes RESTful endpoints that return real-time system metrics in **JSON format**, making it easy to integrate with dashboards or external monitoring tools.

---

## 🚀 Features

* Get **CPU usage, model, cores, and frequency**
* Monitor **memory usage** (RAM and swap)
* Query **disk details and mounted partitions**
* Retrieve **network interface stats** (MAC, IP, speed, RX/TX)
* List **top processes by CPU or memory**
* Read **system sensors** (temperature, fan speed, voltage) where supported
* Fetch **battery details** (for laptops)
* Expose **system and OS info**
* Lightweight and cross-platform (Linux, Windows, macOS)

---

## 📦 Tech Stack

* **Java 17+**
* **Spring Boot 3+**
* **OSHI** (Operating System and Hardware Information library)
* **Maven** for build & dependency management

---

## 🔗 API Endpoints

### System Info

```
GET /api/system/info
```

Returns OS details, uptime, and JVM info.

### CPU

```
GET /api/system/cpu
```

Returns CPU model, cores, load, and frequency.

### Memory

```
GET /api/system/memory
```

Returns total/available RAM and swap usage.

### Disk

```
GET /api/system/disk
```

Returns disk sizes, usage, and I/O stats.

### File System

```
GET /api/system/filesystem
```

Returns mounted partitions with total/used/free space.

### Network

```
GET /api/system/network
```

Returns interfaces, MAC, IPs, speeds, RX/TX bytes.

### Processes

```
GET /api/system/processes?limit=5&sort=cpu
```

Returns top processes by CPU or memory usage.

### Sensors

```
GET /api/system/sensors
```

Returns CPU temperature, fan speeds, and voltage (if supported).

### Battery

```
GET /api/system/battery
```

Returns battery percentage and charging state (if available).

---

## ⚙️ Running the Project

### Prerequisites

* Java 17+
* Maven 3+
* Git

### Steps

```sh
# Clone the repo
git clone https://github.com/innaesim/system-monitor-api.git
cd system-monitor-api

# Build the project
mvn clean install

# Run the API
mvn spring-boot:run
```

The API will start on **[http://localhost:8080](http://localhost:8080)**

---

## 📊 Example Response

**GET** `/api/system/cpu`

```json
{
  "vendor": "Intel",
  "model": "Core i7-10750H",
  "logicalCores": 12,
  "physicalCores": 6,
  "frequencyGHz": 2.60,
  "systemLoad": 12.5
}
```

---


## 📜 License

This project is licensed under the MIT License.

---
