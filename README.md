<h1>CRUD-Quarkus</h1>

> Status: Finalizado

## Contents
  
* [O que é?](#what-is-it)
* [Requesitos](#requirements)
* [Tecnologias Usadas](#technologies)
* [Instalação](#installation)
* [Executando a aplicação](#run-application)

## <a name="what-is-it"></a>What is it?

- Um CRUD utilizando Quarkus e SQL Server

## <a name="requirements"></a>Requesitos

- Java 12+
- Quarkus
- Microsoft SQL Server

## <a name="technologies"></a>Tecnologias Usadas

- Java
- Quarkus
- MySQL
- Flyway

## <a name="installation"></a>Instalação

- Clone o repositório para o seu dispositivo;
- Importe-o como um 'Existing MAVEN Project' em sua IDE;
- Edite o arquivo app application.properties informando a senha do seu banco de dados SQL server e o schema do banco de dados.

### Application.properties
```xml
%dev.quarkus.datasource.password=${yourPassword}
%dev.quarkus.datasource.jdbc.url=jdbc:sqlserver://localhost:1433;database=${yourDatabase}
.
.
.
quarkus.flyway.schemas=${yourDatabase}
```

## <a name="run-application"></a>Executando a aplicação


<h3>Windows</h3>

- Abra um terminal na pasta CRUD-Quarkus/Api e digite: 

```xml
./mvnw compile quarkus:dev
```

<h3>Linux</h3>

Abra um terminal na pasta CRUD-Quarkus/Api e digite: 

```xml
mvn compile quarkus:dev
```
