# TeachMeSkills_C32_HW_Lesson_39_40

## The following is implemented:
- Implement CRUD operations for User, Product and Security models in Spring MVC
- Add validation to User, Product, Security models
- Implement Global Error Handler

## Testing Security Functionality

This section describes how to test the creation, update, and deletion of `Security` records in the application.

---

### 1. Testing Security Creation

**Endpoint:**  
`POST /security/registration`

**Steps:**

1. Navigate to the registration page:  
   `http://localhost:8080/security/registration`
2. Fill out the registration form with the following fields:
    - First Name
    - Second Name
    - Age
    - Email
    - Sex
    - Telephone Number
    - Login
    - Password
3. Submit the form.
4. After successful registration, you will be redirected to the list of users (`/user/all-users`), where you can verify that the new user and associated security record have been created.

---

### 2. Testing Security Update

**Endpoint:**  
`POST /security/update`

**Steps:**

1. Navigate to the edit page for a specific security record:  
   `http://localhost:8080/security/edit/{id}`  
   Replace `{id}` with the ID of the security record you want to update.
2. On the edit page, update the following fields:
    - Login
    - Password
3. Submit the form.
4. After successful update, you will be redirected to the list of users (`/user/all-users`). Verify that the security record has been updated in the database.

---

### 3. Testing Security Deletion

**Endpoint:**  
`POST /security/delete`

**Steps:**

1. Navigate to the delete confirmation page for a specific security record:  
   `http://localhost:8080/security/delete/{id}`  
   Replace `{id}` with the ID of the security record you want to delete.
2. On the confirmation page, click the "Delete" button.
3. After successful deletion, you will be redirected to the list of users (`/user/all-users`). Verify that the `login` and `password` fields for the security record have been set to `null` in the database.

---


