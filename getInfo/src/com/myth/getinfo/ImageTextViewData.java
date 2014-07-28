package com.myth.getinfo;

public class ImageTextViewData
{

    private String text;

    private String image;

    private ImageTextViewType type;

    public ImageTextViewData(ImageTextViewType type)
    {
        this.setType(type);
    }

    public ImageTextViewData(ImageTextViewType type, String s)
    {
        this.setType(type);
        if (type == ImageTextViewType.IMAGE)
        {
            image = s;
        }
        else
        {
            text = s;
        }
    }

    public ImageTextViewType getType()
    {
        return type;
    }

    public void setType(ImageTextViewType type)
    {
        this.type = type;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    enum ImageTextViewType
    {
        TEXT, IMAGE
    }

}
