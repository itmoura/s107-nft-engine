Feature: NFT

    Serviços para gerenciamento de NFTs

    Scenario: Cadastrar uma nova NFT
        Given O usuario cadastra uma nova coleção de NFTs
        """
        {
            "name": "NFT Collection",
            "description": "NFT Collection Description",
            "created_by": "2516154a-0a34-40a7-9c91-5f9ac2ffa516"
        }
        """
        When  O usuario cadastra uma nova NFT
        """
            {
                "name": "NFT 1",
                "description": "NFT 1",
                "link_image": "link_img",
                "price": 100,
                "type_price": "ETH",
                "collection_id": "2516154a-0a34-40a7-9c91-5f9ac2ffa516",
                "owner_id": "2516154a-0a34-40a7-9c91-5f9ac2ffa516",
                "status": "active",
                "category": "art",
                "tags": "tag1, tag2, tag3",
                "created_by": "2516154a-0a34-40a7-9c91-5f9ac2ffa516"
            }
        """
        Then  O sistema deve retornar o ID da NFT cadastrada