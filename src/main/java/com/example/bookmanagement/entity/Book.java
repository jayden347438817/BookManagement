package com.example.bookmanagement.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
@Data
public class Book {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String author;
    private BigDecimal price;
}
