# Java Client-Server Search Application

## Overview

This project demonstrates a client-server architecture where the server hosts a search database, and the client interacts with it via a graphical user interface (GUI). The user can input search queries, and the server responds with matching results from the database. The results are displayed in a drop-down menu on the client side.

- **Server**: Handles incoming connections and processes search queries.
- **Client**: Sends search requests and displays search results in a GUI.

## Features

- The server listens on a specific port and reads a database from a text file (`searchDatabase.txt`).
- The client connects to the server, sends search queries, and displays matching results using Java Swing components.
- Both the server and client use socket programming for network communication.
- The server supports multiple searches during a single session.

## Technologies

- **Java**: Core language used for both client and server.
- **Swing**: Java GUI library for displaying the search results and user interaction on the client side.
- **Socket Programming**: For client-server communication.
- **File I/O**: To load the search database on the server side.

## Files

- `SearchServer.java`: The server code that listens for client connections, processes search queries, and sends results back to the client.
- `SearchClient.java`: The client code that connects to the server, sends search queries, and displays the results in a GUI.
- `searchDatabase.txt`: The database file containing entries in the format `ID;Title;Description`, used by the server for processing search queries.

## How It Works

### 1. Server

- Reads the `searchDatabase.txt` file into a list.
- Listens on a specified port (e.g., `1234`) for client connections.
- Upon receiving a search query from the client, it searches the database for titles and descriptions containing the query string.
- Sends back the matching titles to the client.

### 2. Client

- Connects to the server using a hostname (e.g., `localhost`) and port number.
- Prompts the user to enter search text and sends it to the server.
- Receives search results from the server and displays them in a drop-down list.
- The client continues to prompt for searches until the user decides to quit.

## Setup Instructions

### 1. Server Setup

1. Ensure you have `searchDatabase.txt` available in the same directory as `SearchServer.java`.
2. Compile and run the `SearchServer.java` file:
   ```bash
   javac SearchServer.java
   java SearchServer
