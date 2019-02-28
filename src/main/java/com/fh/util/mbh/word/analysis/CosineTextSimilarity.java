/**
 *
 * APDPlat - Application Product Development Platform
 * Copyright (c) 2013, 杨尚川, yang-shangchuan@qq.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.fh.util.mbh.word.analysis;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fh.util.mbh.word.segmentation.Word;
import com.fh.util.mbh.word.util.AtomicFloat;


/**
 * 文本相似度计算
 * 判定方式：余弦相似度，通过计算两个向量的夹角余弦值来评估他们的相似度
 * 余弦夹角原理：
 * 向量a=(x1,y1),向量b=(x2,y2)
 * similarity=a.b/|a|*|b|
 * a.b=x1x2+y1y2
 * |a|=根号[(x1)^2+(y1)^2],|b|=根号[(x2)^2+(y2)^2]
 * @author 杨尚川
 */
public class CosineTextSimilarity extends TextSimilarity {
    /**
     * 判定相似度的方式：余弦相似度
     * 余弦夹角原理：
     * 向量a=(x1,y1),向量b=(x2,y2)
     * similarity=a.b/|a|*|b|
     * a.b=x1x2+y1y2
     * |a|=根号[(x1)^2+(y1)^2],|b|=根号[(x2)^2+(y2)^2]
     * @param words1 词列表1
     * @param words2 词列表2
     * @return 相似度分值
     */
    @Override
    protected double scoreImpl(List<Word> words1, List<Word> words2) {
        //用词频来标注词的权重
        taggingWeightWithWordFrequency(words1, words2);
        //构造权重快速搜索容器
        Map<String, Float> weights1 = toFastSearchMap(words1);
        Map<String, Float> weights2 = toFastSearchMap(words2);
        //所有的不重复词
        Set<Word> words = new HashSet<>();
        words.addAll(words1);
        words.addAll(words2);
        //向量的维度为words的大小，每一个维度的权重是词频
        //a.b
        AtomicFloat ab = new AtomicFloat();
        //|a|的平方
        AtomicFloat aa = new AtomicFloat();
        //|b|的平方
        AtomicFloat bb = new AtomicFloat();
        //计算
        words
            .parallelStream()
            .forEach(word -> {
                Float x1 = weights1.get(word.getText());
                Float x2 = weights2.get(word.getText());
                if (x1 != null && x2 != null) {
                    //x1x2
                    float oneOfTheDimension = x1 * x2;
                    //+
                    ab.addAndGet(oneOfTheDimension);
                }
                if (x1 != null) {
                    //(x1)^2
                    float oneOfTheDimension = x1 * x1;
                    //+
                    aa.addAndGet(oneOfTheDimension);
                }
                if (x2 != null) {
                    //(x2)^2
                    float oneOfTheDimension = x2 * x2;
                    //+
                    bb.addAndGet(oneOfTheDimension);
                }
            });
        //|a|
        double aaa = Math.sqrt(aa.doubleValue());
        //|b|
        double bbb = Math.sqrt(bb.doubleValue());
        //使用BigDecimal保证精确计算浮点数
        //|a|*|b|
        //double aabb = aaa * bbb;
        BigDecimal aabb = BigDecimal.valueOf(aaa).multiply(BigDecimal.valueOf(bbb));
        //similarity=a.b/|a|*|b|
        //double cos = ab.get() / aabb.doubleValue();
        double cos = BigDecimal.valueOf(ab.get()).divide(aabb, 9, BigDecimal.ROUND_HALF_UP).doubleValue();
        return cos;
    }

    public static void main(String[] args) {
        String text1 = "9月23日茂名市天气预报 多云有中雷雨 局部大雨 _ 一起来关注市的，今天是24节气中的秋分节气，太阳直射赤道昼夜等长，通常秋分之后我国大部地区都进入到了秋季，开始了一场秋雨一场寒，但是茂名市却依旧在炎炎夏季，太阳是依然强烈，不过早晚似乎也多了一丝清凉的风。今天（9月22日）茂名市的还算不错，除了中午有点热和有点晒，从明天开始这样的情况将会有所变化，预计今晚（9月22日）到明天白天，茂名市天气趋于不稳定的，天空云量增多有雷阵雨，局部雨势会比较大，气温方面是24到32度；到了9月24日也就是周六，茂名市的降雨会有所增强，会有大雨到的降水出现；9月25日全市多云有分散阵雨，这两天在气温方面变化不大。未来两天茂名市的天气不会太晒了，不过一般下雨的时候都会出现交通拥堵的情况，所以在这要提醒广大司机朋友们一定要小心驾驶。了解具体的天气情况，一起来关注具体的区： 多云有中雨  局部大雨      气温24-32℃区： 多云有中雷雨  局部大雨     气温25-31℃ 区： 多云有雷阵雨  局部大雨     气温24-32℃区： 多云有中雷雨  局部大雨     气温25-31℃区： 多云有雷阵雨  局部大雨     气温24-31℃及时了解天气预报，就上，旗下的为您提供查询以及国际，帮您及时了解天气情况和，方便安排日常生活和出行。";
        String text2 = "天气预报，小雨转雨夹雪或小雪，气温持续偏低，雷阵雨，小雨转中雨，最高气，中雨转大雨，东部有阵雨或雷阵雨，东南风2-3级，温度18～29℃，有效降水";
        TextSimilarity textSimilarity = new CosineTextSimilarity();
        double score1pk2 = textSimilarity.similarScore(text1, text2);
        System.out.println(text1+" 和 "+text2+" 的相似度分值："+score1pk2);
    }
}
