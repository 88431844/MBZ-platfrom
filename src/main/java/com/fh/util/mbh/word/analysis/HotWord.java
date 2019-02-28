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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fh.util.mbh.word.recognition.Punctuation;
import com.fh.util.mbh.word.recognition.RecognitionTool;

/**
 * 利用NGRAM做热词分析
 * @author 杨尚川
 */
public class HotWord {
    private static final Logger LOGGER = LoggerFactory.getLogger(HotWord.class);
    public static Map<String, Integer> get(String text, int ngram){
        Map<String, Integer> map = new HashMap<>();
        //根据标点符号对文本进行分割
        //根据英文单词对文本进行分割
        List<String> sentences = new ArrayList<>();
        for(String sentence : Punctuation.seg(text, false)){
            LOGGER.debug("判断句子是否有英文单词：{}", sentence);
            int start=0;
            for(int i=0; i<sentence.length(); i++){
                if(RecognitionTool.isEnglish(sentence.charAt(i))){
                    if(i>1 && !RecognitionTool.isEnglish(sentence.charAt(i-1))){
                        sentences.add(sentence.substring(start,i));
                        start=i+1;
                    }else{
                        start++;
                    }
                }
                if(i==sentence.length()-1){
                    sentences.add(sentence.substring(start,i+1));
                }
            }
        }
        for(String sentence : sentences){
            LOGGER.debug("\n\n分析文本：{}", sentence);
            int len = sentence.length()-ngram+1;
            for(int i=0; i<len; i++){
                String word = sentence.substring(i, i+ngram);
                System.out.print(word+" ");
                Integer count = map.get(word);
                if(count == null){
                    count = 1;
                }else{
                    count++;
                }
                map.put(word, count);
            }
        }
        return map;
    }
    public static void main(String[] args){
        Map<String, Integer> map = get("天气网讯，再度过昨天(24日)的腊八节后，北京气温仍然低迷，最低气温已跌至-12.9℃。据市气象台预报，北京今天(25日)蓝天回归，但最高气温仍在冰点以下，体感寒冷，未来三天依旧保持晴转多云天气，而防寒保暖仍是重中之重。北京气温低迷上周日夜间，京城今冬初雪悄然而至，虽然雪未在城区多做停留，但寒冷却有点儿留下不走的意思，气温一蹶不振，最高气温已连续4天位于冰点以下，是今冬以来的最冷一周。今晨，北京依旧晴冷，,AQI(空气质量指数)仅有17，达优良标准，天空颜值继续在线。北京市气象台预计，今天白天晴转多云，北风3级左右转南风2级，最高气温-3℃;夜间多云间晴，南转北风1、2级，最低气温-11℃。北京天气预报01月25日 今天 多云 -10~-4℃ 优 东北风 2级01月26日 明天 多云 -10~-3℃ 优 东南风 2级01月27日 后天 多云 -9~-1℃ 良 东北风 1级01月28日 星期日 晴 -8~0℃ 优 西北风 3级01月29日 星期一 晴 -7~1℃ 优 西北风 3级01月30日 星期二 晴 -5~4℃ 优 西北风 2级01月31日 星期三 晴 -5~4℃ 优 西北风 3级气象专家提醒，目前仍持续低温蓝色预警中，今明天，北京平原地区最低气温仍在-10℃左右，请注意防寒保暖，谨防感冒。", 2);

        map.entrySet().stream().sorted((a,b)->b.getValue().compareTo(a.getValue())).forEach(e->
                        LOGGER.info(e.getKey()+"\t"+e.getValue())
        );
    }
}
