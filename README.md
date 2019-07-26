Roteiro de Teste do Sistema

Pré-Requisitos:

Banco PostgreSQL

username: postgres
pass: postgres
db: salesorderdb

Inserção de Fabricante:
POST -> localhost:8080/manufacturer/insert
Request Body:
{
	"name": "Quality farm goods"
}

Response: 1

Request Body:
{
	"name": "Alfa Chemical Industry"
}
	
Response: 2

Recuperação de Fabricante:
GET -> localhost:8080/manufacturer/get/1?fields=id,name

Response Body:
{
	"id": 1,
	"name": "Quality farm goods"
}


Inserção de Produto:
 
POST -> localhost:8080/product/insert
{
  "name": "Grape juice",
  "description": "Natural grape juice",
  "barcode": "7002085002679",
  "manufacturer": {
    "id": 1
  },
  "unitPrice": 25.0
}

POST -> localhost:8080/product/insert
{
  "name": "Banana Vitamine",
  "description": "Natural banana vitamine",
  "barcode": "7002085002522",
  "manufacturer": {
    "id": 1
  },
  "unitPrice": 30.0
}

POST -> localhost:8080/product/insert
{
  "name": "Calcium",
  "description": "Calcium Additive",
  "barcode": "700208500223",
  "manufacturer": {
    "id": 2
  },
  "unitPrice": 35.0
}


GET -> localhost:8080/product/get/1?fields=id,description,barcode,manufacturer.id,manufacturer.name,unitPrice

Response Body:
{
  "id": 1,
  "description": "Natural grape juice",
  "barcode": "7002085002679",
  "manufacturer": {
    "id": 1,
    "name": "Quality farm goods"
  },
  "unitPrice": 18.55
}

Ordenados por nome:
GET -> localhost:8080/product/findProducts?fields=id,name
[
    {
        "id": 2,
        "name": "Banana Vitamine"
    },
    {
        "id": 3,
        "name": "Calcium"
    },
    {
        "id": 1,
        "name": "Grape juice"
    }
]

POST -> localhost:8080/salesorder/insert
{
  "products": [
    { "id": 1, "units": 2 },
    { "id": 2, "units": 2.5 },
    { "id": 3, "units": 4 }
  ],
  "consumer": {
    "name": "John Doe",
    "phone": "+554512345678",
    "email": "some@one.com"
  },
  "payment": {
    "mode": "bank slip",
    "installments": 3
  },
  "delivery": {
    "mode": "in-store withdrawal"
  }
}


Response:

{
    "id": 1,
    "products": [
        {
            "id": 1,
            "name": "Grape juice",
            "description": "Natural grape juice",
            "barcode": "7002085002679",
            "unitPrice": 25
        },
        {
            "id": 2,
            "name": "Banana Vitamine",
            "description": "Natural banana vitamine",
            "barcode": "7002085002522",
            "unitPrice": 30
        },
        {
            "id": 3,
            "name": "Calcium",
            "description": "Calcium Additive",
            "barcode": "700208500223",
            "unitPrice": 35
        }
    ],
    "consumer": {
        "id": 3,
        "name": "John Doe",
        "phone": "+554512345678",
        "email": "some@one.com"
    },
    "payment": {
        "mode": "bank slip",
        "amountPrice": 265,
        "installments": 3,
        "installmentPrice": 88.2999999999999972
    },
    "delivery": {
        "mode": "in-store withdrawal"
    }
}



