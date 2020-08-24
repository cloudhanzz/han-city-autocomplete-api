## City AutoComplete API

### Time  Used
Started from Saturday and ended on Sunday.

### Requirements

The full system requirements can be found in the enclosed `coding-challenge.pdf`

Here is the summary:
- Given a complete or partial query term with the optional latitude and longitude, the API can
  return a list of places that most match the intended place.
- The resulted places are sorted by their matching scores in descending order.

### Solution

#### Preparing the Dataset

I wrote a shell script to download the needed files from http://www.geonames.org/ and prepared them
to the format needed by this application. As the whole dataset is as big as 1.5G, too big to be
uploaded to GitHub, I kept only American and Canadian dataset. Please see `scripts/geo-prepper.sh`
for details on how the data is prepared.

#### Algorithm

- Although not specified in the requirement, I used a result `limit` to prevent the system from
  blowing up. Without this guard, a query term like `p` with no latitude and longitude will result in
  hundreds of thousands of matching places, which we don't want. You can tweak this number from the
  `src/main/resources/application.properties` file.

- The similarity score between two strings is calculated by using the `apache.commons.text` library.

- The geographic distance between two coordinates is computed by using an implementation of
  `Haversine Algorithm`.
   
- When no latitude or longitude is provided, the search is simply a linear search, no preference is
  given to any area and the scores are solely based on the string similarities.
  
- When a latitude and a longitude are provided, the score is calculated by the following formula:
 
  `total-score = string-similarity-score * name-weight + geo-distance-score * coordinate-weight`
   
  where the two weights can be adjusted from the `application.properties` file as well.
  
- With latitude and longitude provided, search starts from the state/province containing that coordinate.
  After that, search continues in the rest states/provinces in the optimal path I computed, though
  I have to admit that the paths are quite coarse. See `han.jiayun.city.autocomplete.service.impl.OptimalPathFinder`
  for implementation details.
  
- Evolutionary computing is used to keep replacing the unhealthy suggestions with the healthier ones.
  This evolution occurs only after the pool of the suggestions is as big as the threshold indicated
  by the `limit variable`
  
- You can check the log messages to see the search path and the evolutionary process that lead to
  the best autocomplete suggestions.
  
- To convert the geographic distance into a matching score, I provided a set of heuristic rules, which
  can be found in `han.jiayun.city.autocomplete.service.impl.CoordinateScoringServiceImpl`.
 
 #### han-city-autocomplete-api
 
 - Respecting the requirement to use the latest Java version, I used Java 11. Therefore, to run the 
   application, you must have Java 11 installed. Both Oracle and OpenJDK are fine. If you have no
   Java 11 and don't want to install it, let me know so that I can modify the code to downgrade to 
   Java 8.
   
 - As the application use `lombok`, you need to install its plugin in your IDE if you want to run it
   from IDE. However, it is not needed to run it from command line using maven.
   
 - `Spring Boot 2.3.3` is used, which is the latest version as of now. 
 
 - The default port: `9998`
 
 - To run the application using maven, issue this command: `mvn clean spring-boot:run`
 
 - To run it from within IDE: the entry class is: `han.jiayun.city.autocomplete.Application`
 
 - To run the tests from maven, use this command: `mvn clean test` but I recommend using IDE as it 
   brings up more detailed test information, which also serve as the application's documentation.
   
 - After the application is running, you can see its full API definition using [Swagger UI at: ]
   http://localhost:9998/swagger-ui.html#/suggestions45controller/getSuggestionsUsingGET.
 
#### Quality Assurance

- Totally **541** test cases were written, covering almost all services, utility classes, model 
  classes, and others. These tests let me code with confidence.
  
#### Deliverable

Par the requirement, this application does not use third-party services, nor does it need to download
anything, as all is prepared by me. After initial maven installation, you can run the application
completely offline, meaning you can even run it with your internet cut off.
  
### Where to Improve
The following aspects can be improved.
- The optimal search paths can be refined.

- Cache can be used. For almost all search applications, good cache strategy can make a big difference,
  which, due to the time limit, I have not touched that area.
  
### Bonus Points

##### Can you think of more scoring parameters to make the suggestions better?

The following parameters can be included to enhance the search:

- The population
- The maximal number of search results
- The minimal matching score
- The maximal geographic distance to be allowed

#### Sketch out a design for per user API keys and billing

For this to happen, authentication and authorization are needed. Ideally, OAuth2 should be used for 
Authorization and OpenID connect can be used for authentication. With those ready, an API Gateway can
be used plus a well-designed metering component.
