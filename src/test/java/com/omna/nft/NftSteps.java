package com.omna.nft;

import com.omna.nft.model.dto.CollectionsNFTDTO;
import com.omna.nft.model.dto.CreateNFTDTO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NftSteps extends CucumberSteps {

    private CreateNFTDTO createNFTDTO;
    private CollectionsNFTDTO collectionsNFTDTO;

    @Given("O usuario cadastra uma nova coleção de NFTs")
    public void o_usuario_cadastra_uma_nova_coleção_de_nfts(String json) throws Exception {
        getMockMvc().perform(post("/api/nft/v1/collections")
                .content(json).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(mvcResult -> {
            System.out.println(mvcResult.getResponse().getContentAsString());
        })
        .andExpect(status().isCreated());
    }

    @When("O usuario cadastra uma nova NFT")
    public void oUsuarioCadastraUmaNovaNFT(String json) {
        System.out.println("O usuario cadastra uma nova NFT");
        System.out.println(json);
    }

    @Then("O sistema deve retornar o ID da NFT cadastrada")
    public void oSistemaDeveRetornarOIDDaNFTCadastrada() {
        System.out.println("O sistema deve retornar o ID da NFT cadastrada");
    }
}
