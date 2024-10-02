### API Documentation

#### GET /price

**Description:**
Retrieves the price of a product for a given date, product ID, and brand ID.

**Request Parameters:**
- `date` (required): The date for which the price is requested. Format: `yyyy-MM-dd'T'HH:mm:ss`.
- `product_id` (required): The unique identifier of the product.
- `brand_id` (required): The unique identifier of the brand.

**Example Request:**
```sh
curl -X GET "http://localhost:8080/price?date=2020-06-14T10:00:00&product_id=35455&brand_id=1" -H "accept: application/json"
```

**Responses:**
- **200**: Successfully retrieved the price.
    - Body:
      ```json
      {
        "productId": 35455,
        "brandId": 1,
        "price": 35.50,
        "startDate": "2020-06-14T00:00:00",
        "endDate": "2020-12-31T23:59:59"
      }
      ```
- **404**: No price found for the given parameters.
- **500**: Internal Server Error.
