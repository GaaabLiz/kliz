package io.github.gaaabliz.kliz.common.model

/**
 * Represents common file suffixes for text-based files.
 *
 * @property suffix The string representation of the file suffix (e.g., ".json").
 */
enum class TextFileSuffix( val suffix : String) {
    /** JSON file suffix. */
    JSON(".json"),
    /** Plain text file suffix. */
    TXT(".txt"),
    /** Comma Separated Values file suffix. */
    CSV(".csv"),
    /** Markdown file suffix. */
    MARKDOWN(".md")
}