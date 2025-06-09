# Spring Boot Saga Pattern с использованием Temporal

Этот проект демонстрирует реализацию паттерна Saga с использованием Temporal в Spring Boot приложении. Паттерн Saga используется для управления распределенными транзакциями в микросервисной архитектуре.

## Обзор проекта

Проект состоит из следующих компонентов:

1. **order-service-temporal** - сервис заказов, который использует Temporal для оркестрации саги
2. **mock-services** - имитация внешних сервисов (платежи, инвентарь, доставка)
3. **mock-service-client** - клиентская библиотека для взаимодействия с mock-services
4. **temporal** - конфигурация Temporal сервера

## Архитектура

Проект реализует паттерн Saga с использованием Temporal для оркестрации. Процесс обработки заказа состоит из трех шагов:

1. Обработка платежа
2. Резервирование товара на складе
3. Создание доставки

Для каждого шага определены компенсирующие действия, которые выполняются в случае сбоя:

1. Отмена платежа
2. Освобождение зарезервированного товара
3. Отмена доставки

Temporal обеспечивает надежное выполнение этих шагов и компенсирующих действий, даже в случае сбоев.

## Требования

- Java 17 или выше
- Docker и Docker Compose
- IntelliJ IDEA

## Установка и запуск

### 1. Клонирование репозитория

```bash
git clone <url-репозитория>
cd spring-boot-saga-pattern-example
```

### 2. Запуск Temporal сервера

```bash
cd temporal
docker-compose -f docker-compose-postgres.yml up -d
```

Это запустит Temporal сервер с PostgreSQL в Docker. Веб-интерфейс Temporal будет доступен по адресу http://localhost:8088.

### 3. Открытие проекта в IntelliJ IDEA

1. Запустите IntelliJ IDEA
2. Выберите "Open" и укажите путь к корневой папке проекта
3. Дождитесь, пока IntelliJ IDEA импортирует проект и загрузит все зависимости

### 4. Запуск сервисов

Запустите сервисы в следующем порядке:

#### Запуск mock-services

1. Найдите класс `MockServicesApplication` в проекте mock-services
2. Щелкните правой кнопкой мыши на классе и выберите "Run 'MockServicesApplication'"

Сервис запустится на порту 8085.

#### Запуск order-service-temporal

1. Найдите класс `OrderServiceTemporalApplication` в проекте order-service-temporal
2. Щелкните правой кнопкой мыши на классе и выберите "Run 'OrderServiceTemporalApplication'"

Сервис запустится на порту 8080.

## Тестирование

### Использование HTTP-файлов

В проекте есть HTTP-файлы, которые можно использовать для тестирования API:

#### Тестирование order-service

1. Откройте файл `order-service-temporal/order.http`
2. Нажмите на зеленый треугольник рядом с запросом, чтобы отправить POST-запрос на `/api/orders`
3. Это запустит процесс обработки заказа с использованием Temporal

#### Тестирование mock-services напрямую

1. Откройте файл `mock-services/mock.http`
2. Выберите нужный запрос и нажмите на зеленый треугольник, чтобы отправить его

### Мониторинг выполнения саги

1. Откройте веб-интерфейс Temporal по адресу http://localhost:8088
2. Перейдите в раздел "Workflows"
3. Найдите запущенный workflow с ID, начинающимся с "order-"
4. Нажмите на ID, чтобы увидеть детали выполнения workflow

## Настройка имитации сбоев

По умолчанию имитация сбоев отключена. Чтобы включить ее:

1. Откройте файл `mock-services/src/main/resources/application.properties`
2. Измените значение `fault.injection.disabled` на `false`
3. Перезапустите mock-services

При включенной имитации сбоев, mock-services будут случайным образом:
- Добавлять задержки в ответы
- Возвращать ошибки с разными HTTP-статусами

Это позволяет тестировать устойчивость саги к сбоям.

## Структура проекта

### order-service-temporal

- `OrderController` - REST-контроллер для запуска саги
- `OrderWorkflow` - интерфейс Temporal workflow
- `OrderWorkflowImpl` - реализация workflow с логикой саги
- `OrderActivities` - интерфейс Temporal activities
- `OrderActivitiesImpl` - реализация activities, которая вызывает внешние сервисы

### mock-services

- `MockController` - REST-контроллер, имитирующий внешние сервисы
- `FaultInjectionService` - сервис для имитации сбоев

### mock-service-client

- `MockServiceClient` - клиент для вызова mock-services
- `MockClientConfiguration` - конфигурация клиента

## Дополнительная информация

Для более подробной информации о Temporal посетите [официальную документацию Temporal](https://docs.temporal.io/).