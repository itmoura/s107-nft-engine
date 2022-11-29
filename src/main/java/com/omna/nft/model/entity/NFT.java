package com.omna.nft.model.entity;

import com.omna.nft.model.enumeration.Status;
import com.omna.nft.model.enumeration.TypeCoin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "nft")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Data
public class NFT {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "nft_id")
    private UUID nftId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String link_image;

    @Column
    private BigDecimal price;

    @Column
    private TypeCoin typeCoin;

    @ManyToOne
    @JoinColumn(name = "collection_id", referencedColumnName = "collection_id")
    private CollectionsNFT collectionsNft;

    @Column(name = "owner_id")
    private UUID ownerId;

    @Column
    private Status status;

    @Column
    private String category;

    @Column
    private String tags;

    @Column
    private UUID created_by;

    @Column
    private LocalDateTime created_at;

    @Column
    private LocalDateTime updated_at;

    @PrePersist
    public void prePersist() {
        created_at = LocalDateTime.now();
        updated_at = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updated_at = LocalDateTime.now();
    }

}
