package com.example.cardsmvvmcompose.data

data class TmCard(var card_type: String= "", var card: CardAttr= CardAttr()) {
    enum class CardType { TEXT, IMAGE, TITLE, DESCRIPTION }

    fun isText(): Boolean = card_type.contains("text")
    fun isTitle(): Boolean = card_type.contains("title")
    fun isDescription(): Boolean = card_type.contains("description")
    fun isImage(): Boolean = card_type.contains("image")
}

data class CardAttr(
    var value: String?= null,
    var attributes: Attributes?= null,
    var image: ImageAttr?= null,
    var title: CardAttr?= null,
    var description: CardAttr?= null)

data class Attributes(var text_color: String= "#000000", var font: FontAttr= FontAttr())

data class FontAttr(var size: Int= 14)

data class ImageAttr(var url: String= "", var size: ImageSize= ImageSize())

data class ImageSize(var width: Int= 100, var height: Int= 100)

data class Page(var cards: List<TmCard> = emptyList())

data class TmData(var page: Page= Page())