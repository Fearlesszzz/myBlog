package com.example.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.ext.gfm.tables.TablesExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MarkdownUtils {

    public static String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    /*
    * 增加扩展（表格生成）
    * Markdown转换成 html
    * @param markdown
    * @return
    * */
    public static String markdownToHtmlExtensions(String markdown) {
        // 转换table的HTML
        List<Extension> tableExtension = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(tableExtension)
                .build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(tableExtension)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext attributeProviderContext) {
                        return new CustomAttributeProvider();
                    }
                })
                .build();
        return renderer.render(document);
    }

    /*
    * 处理标签属性
    * */
    static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String s, Map<String, String> map) {
            if(node instanceof Link) {
                map.put("target", "_blank");
            }
            if(node instanceof TableBlock) {
                map.put("class", "ui celled table");
            }
        }
    }
}
