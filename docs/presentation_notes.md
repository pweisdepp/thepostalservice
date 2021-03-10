# Comvandee, Echert, Huynh, Lindsay

- Three-tier architecture
- Parse database response for form control
- GET / for formats (should maybe have a dedicated endpoint)
- GET /results?... all parameters in query params. Failure is 500, what are failures? Invalid data?
- Express JS, MongoDB
- DB stores formatting information, like regexes
- Infrequent writes, >>> Reads, no OLTP so eventual consistency is fine. No need for ACID etc
- Trade consistency for scalability
- Previous experience with Express.js
- Dynamic form has _all_ fields, and remove fields based on format data from / endpoint

# Ambrose, Wu

- Resource/REST API
- Request/Response for quick processing
- Transaction script? (I'm not sure what this implies)
- Service Gateway? (It's one service...)
- Idempotent retry? (This isn't a write operation...)
- MySQL, one table for address formats, one table per country
- Several get functions, a write function.
- Search parameters go into path fields
  - How will this be unique? Multiple country formats could overlap in number and meaning
- Other cities in france that include nice, but not paris?
- N/A as specific value?
- No dynamic forms

# Altanai Anh Disha Shrvidya

- High level design has many many boxes
  - (Would have liked two slides)
- Express HTML interface?
- Spring service layer
- SOA in gradle, api, util, microservices, dao, auth
  - What is auth for in the reqs?
- GET with query string
- Very complete result
- 404 missing address; 400 no data
- Incredibly complete format result
  - Each country has a format, with each field stored as boolean
- Turns off form fields dynamically
- Load test results not valid - because all pieces were running locally
- Documentation using swagger and annotations
- shell to seed addreses
- Mongo - one collection per country
- No indexing!? No schema? But highly formatted data... 100x faster than what?

# Ivan Ryan Michael Miriam

- UI - select country, relevant field auto generated.
  - Some validation
- All data in JSON metadata
- Query engine transforms and queries
- DB is uploaded from csv file
- Simplified columens - addres_line1 address_line2 etc
- UUID for PK
- Intended a cache, but didn't have time for it
- Prototype ran locally, causes challenges
- Small data sample
- Handshake!? - websockets are what was familiar
- Wildcard for missing fields

# Colin Ulysses Sarah Drew

- UI / API / DB
- UI, API in nodejs is a generalized document store (used mongo API)
- Document data store for more cloud goodness
- All countries have the same generic fields
- Country with mapping between physical addresses
- Dynamic form, labels and fields
- Comprehensive API

# Ruifei Bruno Jungbok Sam

- RESTful system - localized state on the client
- Cache to minimize redundant call
- Uniform interfaces
- Form JSON files are stored in s3
- AWS Lambda for scaling
- API Gateway for LB, etc
- Dynamic form based on selected country
- Resource RPC over HTTP
  - PUT and POST are backwards
- RDBMS all fields all countries all columns
  - (More a columnar store, but sure)
- Lambda has cold start issues
  - Mitigated by measuring 95%ile

# Ryan Domenic Sam Bailey

- MySQL / Flask / HTMLJS UI
- RPC style quick and easy to implement and run
- Request / Response
  - Acknowledgement not necessary, response calculated quickly
- get countries, get formats, post search
- Worldwide shows all address types (might not be great for user)
- Stateless UI, ask the db every time
  - API and client pick up changes
- DB Table == Country
- Dynamically build SQL string
  - Totally open to injection (could have used sqlchemy)

# Harsha Ryan schang@

- (I couldn't hear Harsha)
- Map objects to eachother
- xmlhttprequest for API calls (why not fetch?)
- Format data missing validation info
- Uniform data schema, easy to add new countries
