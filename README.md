
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

### CPU

```
GET /api/v1/cpu/get
```

Returns CPU model, cores, load, and frequency.

### Memory

```
GET /api/v1/memory/get
```

Returns total/available RAM and swap usage.

### Disk

```
GET /api/v1/disk/get
```

Returns disk sizes, usage, and I/O stats.

### File System

```
GET /api/v1/disk/get
```

Returns mounted partitions with total/used/free space.

### Network

```
GET /api/v1/network/get
```

Returns interfaces, MAC, IPs, speeds, RX/TX bytes.

### Processes

```
GET /api/v1/process/get
```

Returns top processes by CPU or memory usage.

---

## ⚙️ Running the Project

### Prerequisites

* Java 17+
* Maven 3+
* Git

### Steps

```sh
# Clone the repo
git clone https://github.com/innaesim/system_monitor_api.git
cd system_monitor_api

# Build the project
mvn clean install

# Run the API
mvn spring-boot:run
```

The API will start on **[http://localhost:8080](http://localhost:8080)**

---

## 📊 Example Response

**GET** `/api/v1/cpu/get`

```json
{
		"systemLoad": 1.07470703125,
		"processLoad": 0.17054263565891473,
		"availableProcessors": 2,
		"cpuUsagePercent": 11.733333333333333,
		"userUsagePercent": 0,
		"systemUsagePercent": 5.333333333333333,
		"idlePercent": 17.066666666666666,
		"perCoreUsage": [
			18.085106382978726,
			17.708333333333336,
			13.541666666666666,
			14.893617021276595
		]
	}
```

---


## 📜 License

This project is licensed under the MIT License.

---
