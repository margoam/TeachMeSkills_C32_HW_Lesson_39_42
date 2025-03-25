# Testing Objects in Postman Based on a Collection

## Introduction
This guide explains how to test objects using Postman based on a pre-defined collection. The collection provided contains endpoints for managing users, security, and products.

## Understanding the Postman Collection
The collection includes API endpoints categorized into three main groups:

1. **User API**
   - `GET /api/user/all-users` → Retrieve all users
   - `GET /api/user/{user_id}` → Retrieve a specific user
   - `POST /api/user/create` → Create a new user
   - `PUT /api/user/update` → Update a user
   - `DELETE /api/user/{user_id}` → Delete a user

2. **Security API**
   - `POST /api/security/registration` → Register a new user
   - `GET /api/security/{security_id}` → Get security details

3. **Product API**
   - `GET /api/products/all-products` → Retrieve all products
   - `GET /api/products/{product_id}` → Retrieve a specific product
   - `POST /api/products/create` → Create a new product
   - `PUT /api/products/update` → Update a product
   - `DELETE /api/products/{product_id}` → Delete a product

## How to Test API Endpoints in Postman

### 1. Testing `GET` Requests
- Open Postman and select the **Get all users** request.
- Click **Send** and verify the response.
- Expected status: `200 OK`
- Expected body:
  ```json
  [
    {
      "id": 1,
      "firstname": "John",
      "lastname": "Doe",
      "email": "john.doe@example.com"
    }
  ]
  ```
- Validate:
   - The response contains an array.
   - User objects contain valid fields.

### 2. Testing `POST` Requests (Creating a User)
- Select the **Create user** request.
- In the **Body** tab, enter JSON data:
  ```json
  {
    "firstname": "Jane",
    "lastname": "Smith",
    "email": "test1@example.com"
  }
  ```
- Click **Send**.
- Expected status: `201 Created`
- Verify that the response body contains the created user with an assigned `id`.

### 3. Testing `PUT` Requests (Updating a User)
- Select the **Update user** request.
- Modify the JSON body with the updated fields.
- Click **Send**.
- Expected status: `200 OK`
- Verify that the user’s details are updated in the system.

### 4. Testing `DELETE` Requests (Deleting a User)
- Select the **Delete user** request.
- Replace `{user_id}` with an existing user ID.
- Click **Send**.
- Expected status: `204 No Content`
- Ensure that the user is no longer retrievable.

## Writing Tests in Postman
Postman allows scripting using JavaScript. Add assertions under the **Tests** tab for automated validation.

Example test for `GET /api/user/all-users`:
```javascript
pm.expect(pm.response.code).to.equal(200);
let jsonData = pm.response.json();
pm.expect(jsonData).to.be.an('array');
pm.expect(jsonData[0]).to.have.property('firstname');
```

Example test for `POST /api/user/create`:
```javascript
pm.expect(pm.response.code).to.equal(201);
let jsonData = pm.response.json();
pm.expect(jsonData).to.have.property('id');
pm.expect(jsonData.email).to.equal("test1@example.com");
```

