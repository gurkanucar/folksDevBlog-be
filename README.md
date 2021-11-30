
# FolksDev Blog Challange

A simple blog project for FolksDev society which includes their videos and contents.

## Specifications

- Text editor powered by draft.js
- Embedded Youtube video url sharing
- Responsive design


  
## Technologies

**Client:** React, Material UI

**Server:** Java Spring Boot 

**Database:** Mysql

**Containerization:** Docker
## Run the project your own computer

### Clone the project

```bash
  git clone https://github.com/gurkanucar/folksDevBlog-be.git
```


```bash
  git clone https://github.com/gurkanucar/folksdevblog-fe.git
```


### For Backend:

```bash
  cd folksDevBlog-be
```

```bash
  docker-compose up --build
```


### For Frontend:

```bash
  cd folksdevblog-fe
```

Install required packages. It may take some time.

```bash
  npm install
```

Start the application

```bash
  npm run start
```

```bash
  docker-compose up --build
```

:+1: 
## API Documentation

#### You can have a look to [Postman Collection](https://www.postman.com/collections/5f1b9bb8757761a3aeb5)

#### • Get all posts

```http
  GET /api/v1/post/posts
```


#### • Get post by id

```http
  GET /api/v1/post/post/${id}
```

| Parameter | Type     | Explanation                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**. The id of the item to be called |



#### • Create post

```http
  POST /api/v1/post/post/${id}
```

 Only "imageUrl" is not required

```
{
    "name": "postname",
    "imageUrl": "image",
    "details": "details of post",
    "videoUrl": "video",
}
```

#### • Update post by id

```http
  PUT /api/v1/post/post/${id}
```

 "imageUrl" and "deleted" are not required

```
{
    "name": "postname",
    "imageUrl": "image",
    "details": "details of post",
    "videoUrl": "video",
    "deleted": false
}
```

| Parameter | Type     | Explanation                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**. The id of the item to be updated |





#### • Delete post by id

```http
  DELETE /api/v1/post/post/${id}
```

| Parameter | Type     | Explanation                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Long` | **Required**. The id of the item to be deleted |




  
## Tests

Before the test, you need to create a database called "folksdev_blog_test"

Java:
```bash
  mvn clean test
```

  
## Demo

#### [• Backend](https://folksdevblog-be.herokuapp.com)

#### [• Frontend](https://folksdevblog-fe-ccd5bd4qv-gurkanucar.vercel.app)

#### [• Api Collection](https://www.postman.com/collections/5f1b9bb8757761a3aeb5)