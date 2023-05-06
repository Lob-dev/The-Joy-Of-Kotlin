package template

class HtmlTemplateEvaluator(
    private val html: String,
    private val model: Map<String, String>,
) {

    fun render(): String {
        var renderedHtml: String = html
        model.forEach { (key, value) ->
            renderedHtml = html.replace("{{$key}}", value)
        }
        return renderedHtml
    }
}
