Feature: NFT

    Serviços para gerenciamento de NFTs

    Scenario: Cadastrar uma nova NFT
        When  O usuario cadastra uma nova NFT
            """
                {
                    "name": "NFT 1",
                    "description": "NFT 1",
                    "image": "link_img",
                    "price": 100,
                    "type_price": "ETH",
                    "owner_id": "0x1234567890",
                    "owner_name": "User 1",
                    "owner_email": "email@email.com",
                    "owner_phone": "1234567890",
                    "created_at": "2021-10-10 10:10:10",
                    "updated_at": "2021-10-10 10:10:10",
                    "status": "active",
                    "type": "image",
                    "category": "art",
                    "tags": "tag1, tag2, tag3",
                    "created_by": "0x1234567890"
                }
            """
        Then  O sistema deve retornar o ID da NFT cadastrada