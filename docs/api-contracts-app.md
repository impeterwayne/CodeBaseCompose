# API Contracts Catalog (Mobile App)

This document catalogs the network contracts and endpoints used by the Mobile App module of **SkillHub**. The networking layer is built using **Retrofit**, **OkHttp**, and **Skydoves Sandwich** for API response handling and exception modeling.

---

## 🛠️ Networking Infrastructure

* **HTTP Client:** OkHttp with custom timeouts and logger interceptors.
* **REST Framework:** Retrofit 2.
* **Response Wrapper:** Skydoves `ApiResponse` for robust success/error/exception state modeling.
* **Marshalling:** Gson Converter for JSON serialization and deserialization.

---

## 📡 Endpoint Contracts

All API definitions are located in the [ApiService.kt](file:///D:/Quest/CodebaseCompose/core/network/src/main/java/com/genesys/core/network/service/ApiService.kt) interface under the `:core:network` module.

### 1. Get AI Templates Home

Retrieves the structured lists of AI templates and layout categories (collections) displayed on the home page.

* **Endpoint:** `/content/api/and-remove-background/home`
* **HTTP Method:** `GET`
* **Serialization Model:** [ResponseAITemplate](file:///D:/Quest/CodebaseCompose/core/network/src/main/java/com/genesys/core/network/dto/template/ResponseAITemplate.kt)
* **Function Signature:**
  ```kotlin
  @GET("content/api/and-remove-background/home")
  suspend fun getAITemplates(): ApiResponse<ResponseAITemplate>
  ```

#### Response Structure Details

* **`ResponseAITemplate`**
  * `data`: `List<ResponseTemplateCollections>` — Collections of template items grouped by categories.
  * `meta`: `Meta` — Pagination metadata.

* **`ResponseTemplateCollections`**
  * `id`: `String` — Unique identifier of the collection.
  * `code`: `String` — Machine-readable code for the category.
  * `name`: `String` — User-friendly display name.
  * `sort`: `Int` — Sorting hierarchy order.
  * `items`: `List<ResponseTemplate>` — Nested array of templates under this collection.

* **`ResponseTemplate`**
  * `id`: `String` — Unique template identifier.
  * `name`: `String` — Name of the template.
  * `premium`: `Boolean` — Flag specifying if this is a paid/VIP template.
  * `ratio`: `String` — Image aspect ratio (e.g. `"1:1"`, `"16:9"`).
  * `thumbnail`: `String` — URL path to the thumbnail image asset.
  * `resource`: `String` — Path to the source raw asset/file.
  * `sort`: `Int` — Display sorting order.
  * `categoryDocumentId`: `String` — Parent category reference document ID.

---

### 2. Download File (Streaming)

Performs a high-performance raw file streaming download for binary assets (such as template resource files or heavy media) bypassing in-memory buffer limits.

* **Endpoint:** Dynamic URL
* **HTTP Method:** `GET`
* **Annotations:** `@Streaming` (Prevents loading the entire payload directly into memory, streaming it to disk instead)
* **Serialization Model:** `ResponseBody` (Raw binary stream)
* **Function Signature:**
  ```kotlin
  @Streaming
  @GET
  suspend fun downloadFile(@Url fileUrl: String): ResponseBody
  ```

---

## 🛡️ Response Modeling (Sandwich)

The network layer leverages Skydoves **Sandwich** to parse Retrofit calls into standardized, exhaustive states:
* **`ApiResponse.Success`**: Extracted directly using response body mapping.
* **`ApiResponse.Failure.Error`**: Catches HTTP status errors (e.g., `4xx`, `5xx`).
* **`ApiResponse.Failure.Exception`**: Handles network time-outs, serialization failures, or DNS issues.
