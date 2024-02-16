const apiUrl = "http://localhost:8080"; // Your API endpoint
const authUrl = "http://localhost:8081"; // Your Auth endpoint

function register() {
  const username = document.getElementById("registerUsername").value;
  const password = document.getElementById("registerPassword").value;

  fetch(`${authUrl}/register`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ username, password }),
  })
    .then((response) => response.json())
    .then((data) => {
      if (data.id) {
        alert("Registration successful");
      } else {
        alert("Registration failed");
      }
    })
    .catch((error) => {
      console.error("Error during registration:", error);
      alert("Registration failed");
    });
}

function login() {
  const username = document.getElementById("loginUsername").value;
  const password = document.getElementById("loginPassword").value;

  fetch(`${authUrl}/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ username, password }),
  })
    .then((response) => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error("Login failed");
      }
    })
    .then((data) => {
      // Save the JWT token
      localStorage.setItem("authToken", data.token);
      showCustomerData();
    })
    .catch((error) => {
      console.error("Error during login:", error);
      alert("Login failed");
    });
}

function showCustomerData() {
  const authToken = localStorage.getItem("authToken");
  if (!authToken) {
    alert("Not authenticated");
    return;
  }

  fetch(`${apiUrl}/api/customers`, {
    method: "GET",
    headers: {
      Authorization: `Bearer ${authToken}`,
    },
  })
    .then((response) => response.json())
    .then((customers) => {
      const customerListElement = document.getElementById("customerList");
      customerListElement.innerHTML = ""; // Clear existing customer data

      customers.forEach((customer) => {
        const listItem = document.createElement("li");
        listItem.textContent = `Name: ${customer.name}, Email: ${customer.email}`;
        customerListElement.appendChild(listItem);
      });

      // Show the customer data section and hide the login form
      document.getElementById("customerData").style.display = "block";
      document.getElementById("login").style.display = "none";
    })
    .catch((error) => {
      console.error("Error fetching customer data:", error);
    });
}
