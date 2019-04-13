# cv-android

An app that just display CV data fetched from a remote service.

Work is split into data/domain/presentation layers.
- data -> repository that is responsible for fetching the data from the server
- interactor -> processes data from the data layer and provides ready data model for the presentation layer
- presentation -> is responsible for presenting the data to the user and user interaction

# TODO:
- Use MockWebServer or similar to mock network connection in the instrumentation test
