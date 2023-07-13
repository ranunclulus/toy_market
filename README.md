# MiniProject_Basic_HuisuJoe

## Introduce

### Mutsa-Market

멋사 마켓이란 흔히들 사용하는 번개장터, 당근마켓, 중고나라와 같이 사용자가 중고 물건을 판매하고자 글을 올리고, 구매자가 댓글을 달아 거래가 성사되는 플랫폼입니다. 실제 중고 거래 애플리케이션의 간단화 버전으로 중고 물품 거래가 등록된 `SalesItem`, 물품에 달리는 댓글인 `Comment`, 그리고 구매자가 가격을 할인받고자 제안할 수 있는 `Negotiation`으로 구성되어 있습니다.

### Entity Relationship Diagram

![https://private-user-images.githubusercontent.com/87214089/251006878-44837967-cf70-4554-af18-01d5c90b2a8f.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjg4NTQxOTk0LCJuYmYiOjE2ODg1NDE2OTQsInBhdGgiOiIvODcyMTQwODkvMjUxMDA2ODc4LTQ0ODM3OTY3LWNmNzAtNDU1NC1hZjE4LTAxZDVjOTBiMmE4Zi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBSVdOSllBWDRDU1ZFSDUzQSUyRjIwMjMwNzA1JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDIzMDcwNVQwNzIxMzRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0zNGEyZTY4M2E4ZDJjZjVhZTU0YzAxODFkMWZjMzRhZDZkOWRlOWFlNTVkMzVmNjVmNTg4MWE4ZTc4OWUwYzQ5JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.DHNrELSvmFG8Jk99QqWyytKTj2xaS8FekkZYa6jROwM](https://private-user-images.githubusercontent.com/87214089/251006878-44837967-cf70-4554-af18-01d5c90b2a8f.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJrZXkiOiJrZXkxIiwiZXhwIjoxNjg4NTQxOTk0LCJuYmYiOjE2ODg1NDE2OTQsInBhdGgiOiIvODcyMTQwODkvMjUxMDA2ODc4LTQ0ODM3OTY3LWNmNzAtNDU1NC1hZjE4LTAxZDVjOTBiMmE4Zi5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBSVdOSllBWDRDU1ZFSDUzQSUyRjIwMjMwNzA1JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDIzMDcwNVQwNzIxMzRaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0zNGEyZTY4M2E4ZDJjZjVhZTU0YzAxODFkMWZjMzRhZDZkOWRlOWFlNTVkMzVmNjVmNTg4MWE4ZTc4OWUwYzQ5JlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.DHNrELSvmFG8Jk99QqWyytKTj2xaS8FekkZYa6jROwM)
## Resources

### SalesItem

- `POST /items`

    ```json
    # Request Body
    {
        "title": "중고 맥북 팝니다",
        "description": "2019년 맥북 프로 13인치 모델입니다",
        "minPriceWanted": 1000000,
        "writer": "jeeho.dev",
        "password": "1qaz2wsx"
    }
    
    # Response Body
    {
        "message": "등록이 완료되었습니다."
    }
    ```

    - 처음으로 물품을 중고 거래에 등록하는 요청이다.
    - `title`, `description`, `minPriceWanted`, `writer`, `password`이 없을 시에 등록되지 않는다.
    - 초기 `status`는 판매 중 상태로 등록된다.
    - 초기 `image`는 등록되지 않는다.
- `GET /items?page={page}&limit={limit}`

    ```json
    # Request Body
    
    # Response Body
    {
        "content": [
    	      {
                "id": 1,
    						"title": "중고 맥북 팝니다",
    						"description": "2019년 맥북 프로 13인치 모델입니다",
    						"minPriceWanted": 1000000,
                "status": "판매중"
            },
    	      {
                "id": 2,
    						"title": "콜드브루 드립기 팝니다",
    						"description": "ㅈㄱㄴ",
    						"minPriceWanted": 20000,
                "imageUrl": "/static/images/image.png",
                "status": "판매완료"
            },
            // ...
        ],
        "totalPages": 4,
        "totalElements": 100,
        "last": false,
        "size": 25,
        "number": 1,
        "numberOfElements": 25,
        "first": false,
        "empty": false
    }
    ```

    - 등록된 중고 물품을 페이지 단위로 읽어 오는 요청이다.
- `GET /items/{itemId}`

    ```json
    # Request Body
    
    # Response Body
    {
    		"title": "중고 맥북 팝니다",
    		"description": "2019년 맥북 프로 13인치 모델입니다",
    		"minPriceWanted": 1000000,
        "status": "판매중"
    }
    ```

    - 등록된 중고 물품을 물품 하나만 읽어 오는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.
- `PUT /items/{itemId}`

    ```json
    # Request Body
    {
        "title": "중고 맥북 팝니다",
        "description": "2019년 맥북 프로 13인치 모델입니다",
        "minPriceWanted": 1250000,
        "writer": "jeeho.dev",
        "password": "1qaz2wsx"
    }
    
    # Response Body
    {
        "message": "물품이 수정되었습니다."
    }
    ```

    - 등록된 중고 물품을 수정하는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `password`가 물품을 등록할 때와 다르다면 물품이 수정되지 않는다.
- `PUT /items/{itemId}/image`

    ```json
    # Request Body
    image:    image.png (file)
    writer:   jeeho.dev
    password: 1qaz2wsx
    
    # Response Body
    {
        "message": "이미지가 등록되었습니다."
    }
    ```

    - 등록된 중고 물품에 이미지를 등록하는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `password`가 물품을 등록할 때와 다르다면 물품이 수정되지 않는다.
- `DELETE /items/{itemId}`

    ```json
    # Request Body
    {
        "writer": "jeeho.dev",
        "password": "1qaz2wsx"
    }
    
    # Response Body
    {
        "message": "물품을 삭제했습니다."
    }
    ```

    - 등록된 중고 물품을 삭제하는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.

### Comment

- `POST /items/{itemId}/comments`

    ```json
    # Request Body
    {
        "writer": "jeeho.edu",
        "password": "qwerty1234",
        "content": "할인 가능하신가요?"
    }
    
    # Response Body
    {
        "message": "댓글이 등록되었습니다."
    }
    ```

    - 등록된 중고 물품에 댓글을 다는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `writer`, `content`, `password`가 없다면 댓글이 등록되지 않는다.
    - 초기 `reply` 값은 없다.
- `GET /items/{itemId}/comments`

    ```json
    # Request Body
    
    # Response Body
    {
        "content": [
    	      {
                "id": 1,
    				    "content": "할인 가능하신가요?",
                "reply": "아니요"
            },
            // ...
        ],
        "totalPages": 4,
        "totalElements": 100,
        "last": false,
        "size": 25,
        "number": 1,
        "numberOfElements": 25,
        "first": false,
        "empty": false
    }
    ```

    - 등록된 중고 물품에 달린 댓글을 페이지로 보여 주는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - 25 개씩 묶어서 제공한다.
- `PUT /items/{itemId}/comments/{commentId}`

    ```json
    # Request Body
    {
        "writer": "jeeho.edu",
        "password": "qwerty1234",
        "content": "할인 가능하신가요? 1000000 정도면 고려 가능합니다"
    }
    
    # Response Body
    {
        "message": "댓글이 수정되었습니다."
    }
    ```

    - 등록된 중고 물품에 달린 댓글을 수정하는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `commentId`에 해당하는 댓글이 없다면 예외를 발생시킨다.
    - `password`가 처음에 댓글을 달 때 입력한 값과 다르다면 댓글을 수정할 수 없다.
- `PUT /items/{itemId}/comments/{commentId}/reply`

    ```json
    # Request Body
    {
        "writer": "jeeho.dev",
        "password": "1qaz2wsx",
        "reply": "안됩니다"
    }
    
    # Response Body
    {
        "message": "댓글에 답변이 추가되었습니다."
    }
    ```

    - 등록된 중고 물품에 달린 댓글에 답변을 추가하는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `commentId`에 해당하는 댓글이 없다면 예외를 발생시킨다.
    - `password`가 처음에 댓글을 달 때 입력한 값과 다르다면 답글을 달 수 없다.
- `PUT /items/{itemId}/comments/{commentId}/reply`

    ```json
    # Request Body
    {
        "writer": "jeeho.dev",
        "password": "1qaz2wsx",
        "reply": "안됩니다"
    }
    
    # Response Body
    {
        "message": "댓글에 답변이 추가되었습니다."
    }
    ```

    - 등록된 중고 물품에 달린 댓글에 답변을 추가하는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `commentId`에 해당하는 댓글이 없다면 예외를 발생시킨다.
    - `password`가 처음에 댓글을 달 때 입력한 값과 다르다면 답글을 달 수 없다.
- `DELETE /items/{itemId}/comments/{commentId}`

    ```json
    # Request Body
    {
        "writer": "jeeho.edu",
        "password": "qwerty1234"
    }
    
    # Response Body
    {
        "message": "댓글을 삭제했습니다."
    }
    ```

    - 등록된 중고 물품에 달린 댓글을 삭제하는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `commentId`에 해당하는 댓글이 없다면 예외를 발생시킨다.
    - `password`가 처음에 댓글을 달 때 입력한 값과 다르다면 댓글을 삭제할 수 없다.

### Negotiation

- `POST /items/{itemId}/proposals`

    ```json
    # Request Body
    {
        "writer": "jeeho.edu",
        "password": "qwerty1234",
        "suggestedPrice": 1000000
    }
    
    # Response Body
    {
        "message": "구매 제안이 등록되었습니다."
    }
    ```

    - 등록된 중고 물품에 협상을 제안하는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `suggestedPrice`, `password`가 없다면 협상을 등록할 수 없다.
    - `status`는 초기에 “제안”으로 표시된다.
- `GET /items/{itemId}/proposals?writer=jeeho.edu&password=qwerty1234&page=1`

    ```json
    # Request Body
    {
        "writer": "jeeho.edu",
        "password": "qwerty1234",
        "suggestedPrice": 1000000
    }
    
    # Response Body
    {
        "message": "구매 제안이 등록되었습니다."
    }
    ```

    - 등록된 중고 물품에 달린 협상 목록들을 페이지로 읽어 오는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `writer`가 jeeho.edu이고 `password`가 qwert1234인 협상만 읽어 온다.
- `PUT /items/{itemId}/proposals/{proposalId}`

    ```json
    # Request Body
    {
        "writer": "jeeho.edu",
        "password": "qwerty1234",
        "suggestedPrice": 1100000	
    }
    
    # Response Body
    {
        "message": "제안이 수정되었습니다."
    }
    ```

    - 등록된 중고 물품에 달린 협상에서 제시한 가격을 수정하는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `commentId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `password`와 `writer`가 협상한 사람과 다른 경우 수정할 수 없다.
- `PUT /items/{itemId}/proposals/{proposalId}`

    ```json
    # Request Body
    {
        "writer": "jeeho.dev",
        "password": "1qaz2wsx",
        "status": "수락" || "거절"
    }
    
    # Response Body
    {
        "message": "제안이 수정되었습니다."
    }
    ```

    - 등록된 중고 물품에 달린 협상에 대해 물품을 등록한 사람이 협상을 수락하거나 거절하는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `commentId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `password`와 `writer`가 물품 작성자와 다른 경우 수정할 수 없다.
- `PUT /items/{itemId}/proposals/{proposalId}`

    ```json
    # Request Body
    {
        "writer": "jeeho.edu",
        "password": "qwerty1234"
        "status": "확정"
    }
    
    # Response Body
    {
        "message": "구매가 확정되었습니다."
    }
    ```

    - 등록된 중고 물품에 달린 협상에 대해 협상을 확정하는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `commentId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `password`와 `writer`가 협상 작성자와 다른 경우 수정할 수 없다.
    - 기존의 물품 `status`가 수락이 아니라면 확정할 수 없다.

- `DELETE /items/{itemId}/proposals/{proposalId}`

    ```json
    # Request Body
    {
        "writer": "jeeho.edu",
        "password": "qwerty1234"
    }
    
    # Response Body
    {
        "message": "제안을 삭제했습니다."
    }
    ```

    - 등록된 중고 물품에 달린 협상에 대해 협상을 삭제하는 요청이다.
    - `itemId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `commentId`에 해당하는 상품이 없다면 예외를 발생시킨다.
    - `password`와 `writer`가 협상 작성자와 다른 경우 삭제할 수 없다.
