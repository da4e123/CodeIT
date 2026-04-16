## ⚙️ Project Setup Instructions

### Clone repository
```bash
git clone https://github.com/da4e123/CodeIT.git
```

# Backend Setup

- Open project in IntelliJ
- Configure Maven/Gradle
- Install dependencies

# Frontend Setup 
```bash
cd frontend
npm install
```


## 🚀 How to Run

## Backend

You can start the application in two ways:

#### 1. Using Maven (terminal)
```bash
./mvnw spring-boot:run
```

#### 2. Using IDE (IntelliJ / Eclipse)
- Open the project in your IDE
- Navigate to the main class:
- CodeItApplication.java
- Run the main method (▶ Run button)


## Frontend
```bash
  npm run dev
- ```


## 🗄️ Database Configuration
- Database: PostgreSQL
- Update application.properties:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/earthquakes_db
spring.datasource.username=postgres
spring.datasource.password=user123
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```
