package lob.study.kotlin.article.domain.persistence

import lob.study.kotlin.account.domain.persistence.AccountEntity
import lob.study.kotlin.article.domain.model.Article
import lob.study.kotlin.global.persistence.BaseTimeEntity
import javax.persistence.*


@Entity
@Table(name = "account")
class ArticleEntity(
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 255)
    var title: String,

    @Column(nullable = false, length = 1000)
    var content: String,
) : BaseTimeEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    lateinit var account: AccountEntity

    fun updateContent(article: Article) {
        this.title = article.title
        this.content = article.content
    }

    fun mappingAccount(account: AccountEntity) {
        this.account = account
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArticleEntity
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}