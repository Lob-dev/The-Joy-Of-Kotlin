package lob.study.kotlin.account.domain.persistence

import lob.study.kotlin.account.domain.model.Account
import lob.study.kotlin.article.domain.persistence.ArticleEntity
import lob.study.kotlin.global.persistence.BaseTimeEntity
import javax.persistence.*


@Entity
@Table(name = "account")
class AccountEntity(
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true, length = 15)
    var username: String,

    @Column(nullable = false, unique = true, length = 50)
    var email: String,

    @Column(nullable = false, length = 20)
    var password: String,

    @Column(length = 10)
    var role: String
) : BaseTimeEntity() {

    constructor(id: Long? ,username: String, email: String, password: String) : this(id, username, email, password, "USER")

    @OneToMany
    @JoinColumn(name = "article_id")
    lateinit var articles: ArrayList<ArticleEntity>

    fun updateInfo(account: Account) {
        this.username = account.username
        this.password = account.password
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AccountEntity
        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}