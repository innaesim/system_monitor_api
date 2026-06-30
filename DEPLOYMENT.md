# System Monitor API - VPS Deployment Guide

This guide will help you deploy the System Monitor API on your VPS to monitor your server status.

## Prerequisites

- VPS with Ubuntu/Debian or similar Linux distribution
- Docker and Docker Compose installed
- Root or sudo access
- Ports 8085 (API) and optionally 3306 (MySQL) available

## Installation Steps

### 1. Install Docker and Docker Compose

```bash
# Update package index
sudo apt update

# Install Docker
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh

# Install Docker Compose
sudo apt install docker-compose-plugin -y

# Add your user to docker group (optional, to run without sudo)
sudo usermod -aG docker $USER
newgrp docker
```

### 2. Clone or Upload the Project

```bash
# Option A: Clone from Git
git clone https://github.com/innaesim/system_monitor_api.git
cd system_monitor_api

# Option B: Upload files via SCP
# scp -r system_monitor_api/ user@your-vps-ip:/path/to/deployment/
```

### 3. Configure Environment Variables

```bash
# Copy the example environment file
cp .env.example .env

# Edit with secure values
nano .env
```

**Important:** Change all default passwords and generate a new JWT secret:
```bash
# Generate a secure JWT secret
openssl rand -base64 64
```

### 4. Deploy with Docker Compose

```bash
# Build and start the services
docker compose up -d

# Check if services are running
docker compose ps

# View logs
docker compose logs -f
```

### 5. Verify Deployment

```bash
# Check API health
curl http://localhost:8085/api/v1/cpu/get

# Should return: Unauthorized (expected, since you're not logged in)
```

### 6. Login and Get Access Token

```bash
# Login to get JWT token
curl -X POST http://localhost:8085/api/v1/user/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@yourdomain.com",
    "password": "your_admin_password"
  }'

# Response will include a JWT token like:
# {"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."}
```

### 7. Access System Metrics

```bash
# Use the token to access endpoints
TOKEN="your_jwt_token_here"

# Get CPU statistics
curl http://localhost:8085/api/v1/cpu/get \
  -H "Authorization: Bearer $TOKEN"

# Get Memory statistics
curl http://localhost:8085/api/v1/memory/get \
  -H "Authorization: Bearer $TOKEN"

# Get Disk statistics
curl http://localhost:8085/api/v1/disk/get \
  -H "Authorization: Bearer $TOKEN"

# Get Network statistics
curl http://localhost:8085/api/v1/network/get \
  -H "Authorization: Bearer $TOKEN"

# Get Process statistics
curl http://localhost:8085/api/v1/process/get \
  -H "Authorization: Bearer $TOKEN"
```

## API Documentation

Once deployed, you can access the interactive API documentation (Swagger UI) at:
```
http://your-vps-ip:8085/swagger-ui/index.html
```

## Available Endpoints

### Authentication
- `POST /api/v1/user/login` - Login and get JWT token

### Monitoring Endpoints (require authentication)
- `GET /api/v1/cpu/get` - Get CPU statistics
- `GET /api/v1/memory/get` - Get memory usage
- `GET /api/v1/disk/get` - Get disk usage
- `GET /api/v1/network/get` - Get network statistics
- `GET /api/v1/process/get` - Get process information

## Firewall Configuration

If you have a firewall enabled, allow the API port:

```bash
# UFW (Ubuntu)
sudo ufw allow 8085/tcp
sudo ufw reload

# Firewalld (CentOS/RHEL)
sudo firewall-cmd --permanent --add-port=8085/tcp
sudo firewall-cmd --reload
```

## Remote Access

To access from your local machine:

```bash
# Replace YOUR_VPS_IP with your actual VPS IP address
curl http://YOUR_VPS_IP:8085/api/v1/cpu/get \
  -H "Authorization: Bearer $TOKEN"
```

## Useful Docker Commands

```bash
# Stop services
docker compose down

# Restart services
docker compose restart

# View logs
docker compose logs -f api
docker compose logs -f mysql

# Update and redeploy
git pull  # if using git
docker compose down
docker compose build --no-cache
docker compose up -d

# Clean up (removes containers and volumes)
docker compose down -v
```

## Troubleshooting

### API won't start
```bash
# Check logs
docker compose logs api

# Common issues:
# 1. Database not ready - wait a few seconds and check again
# 2. Port already in use - change SERVER_PORT in docker-compose.yml
```

### Can't connect to database
```bash
# Check MySQL is running
docker compose ps mysql

# Check MySQL logs
docker compose logs mysql

# Restart MySQL
docker compose restart mysql
```

### Memory/CPU readings seem wrong
The API needs access to system metrics. Ensure the container has proper permissions:
```bash
# Add privileged mode in docker-compose.yml under the api service:
# privileged: true
```

## Security Recommendations

1. **Use strong passwords** - Change all default passwords in `.env`
2. **Generate new JWT secret** - Use a unique, random secret
3. **Use HTTPS** - Set up a reverse proxy with Nginx/Caddy and SSL certificate
4. **Limit access** - Use firewall rules to restrict access to trusted IPs
5. **Regular updates** - Keep Docker images and dependencies updated
6. **Backup database** - Regularly backup the MySQL data volume

## Nginx Reverse Proxy with SSL (Optional but Recommended)

```nginx
server {
    listen 80;
    server_name monitor.yourdomain.com;
    return 301 https://$server_name$request_uri;
}

server {
    listen 443 ssl http2;
    server_name monitor.yourdomain.com;

    ssl_certificate /etc/letsencrypt/live/monitor.yourdomain.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/monitor.yourdomain.com/privkey.pem;

    location / {
        proxy_pass http://localhost:8085;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

## Support

For issues or questions, refer to the main README.md or create an issue on GitHub.
