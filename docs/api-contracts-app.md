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

### 1. Fetch Pokemon List

Retrieves a paginated list of Pokemon.

* **Endpoint:** `https://pokeapi.co/api/v2/pokemon`
* **HTTP Method:** `GET`
* **Query Parameters:**
  * `limit` (Int): Maximum number of items to return in the page.
  * `offset` (Int): Pagination offset index.
* **Serialization Model:** [ResponsePokemonList](file:///D:/Quest/CodebaseCompose/core/network/src/main/java/com/genesys/core/network/dto/pokedex/ResponsePokemonList.kt)
* **Function Signature:**
  ```kotlin
  @GET("https://pokeapi.co/api/v2/pokemon")
  suspend fun fetchPokemonList(
      @Query("limit") limit: Int,
      @Query("offset") offset: Int
  ): ApiResponse<ResponsePokemonList>
  ```

#### Response Structure Details

* **`ResponsePokemonList`**
  * `count`: `Int` — Total count of all available Pokemon.
  * `next`: `String?` — URL path to retrieve the next page.
  * `previous`: `String?` — URL path to retrieve the previous page.
  * `results`: `List<ResponsePokemonItem>` — List of Pokemon summary items.

* **`ResponsePokemonItem`**
  * `name`: `String` — Name of the Pokemon.
  * `url`: `String` — Details URL for the Pokemon.

---

### 2. Fetch Pokemon Info

Retrieves high-fidelity details about a specific Pokemon, including dimensions, base statistics, and elemental types.

* **Endpoint:** `https://pokeapi.co/api/v2/pokemon/{name}`
* **HTTP Method:** `GET`
* **Path Parameters:**
  * `name` (String): The case-sensitive name of the Pokemon.
* **Serialization Model:** [ResponsePokemonDetail](file:///D:/Quest/CodebaseCompose/core/network/src/main/java/com/genesys/core/network/dto/pokedex/ResponsePokemonDetail.kt)
* **Function Signature:**
  ```kotlin
  @GET("https://pokeapi.co/api/v2/pokemon/{name}")
  suspend fun fetchPokemonInfo(
      @Path("name") name: String
  ): ApiResponse<ResponsePokemonDetail>
  ```

#### Response Structure Details

* **`ResponsePokemonDetail`**
  * `id`: `Int` — Unique identifier/index of the Pokemon.
  * `name`: `String` — Unique name.
  * `height`: `Int` — Body height.
  * `weight`: `Int` — Body weight.
  * `baseExperience`: `Int` — Base experience yielded upon defeat.
  * `stats`: `List<ResponseStatSlot>` — Statistics breakdown list.
  * `types`: `List<ResponseTypeSlot>` — Types/elements breakdown list.

* **`ResponseStatSlot`**
  * `baseStat`: `Int` — Numerical statistic value.
  * `stat`: `ResponseStatItem` — Associated statistic name/metadata.

* **`ResponseStatItem`**
  * `name`: `String` — Stat name (e.g. `"hp"`, `"attack"`, `"defense"`).

* **`ResponseTypeSlot`**
  * `type`: `ResponseTypeItem` — Associated type name/metadata.

* **`ResponseTypeItem`**
  * `name`: `String` — Element type name (e.g. `"grass"`, `"poison"`).

---

## 🛡️ Response Modeling (Sandwich)

The network layer leverages Skydoves **Sandwich** to parse Retrofit calls into standardized, exhaustive states:
* **`ApiResponse.Success`**: Extracted directly using response body mapping.
* **`ApiResponse.Failure.Error`**: Catches HTTP status errors (e.g., `4xx`, `5xx`).
* **`ApiResponse.Failure.Exception`**: Handles network time-outs, serialization failures, or DNS issues.
