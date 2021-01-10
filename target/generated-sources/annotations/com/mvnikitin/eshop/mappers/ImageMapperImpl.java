package com.mvnikitin.eshop.mappers;

import com.mvnikitin.eshop.dto.ImageDTO;
import com.mvnikitin.eshop.model.Image;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-06T21:43:51+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11 (Oracle Corporation)"
)
@Component
public class ImageMapperImpl implements ImageMapper {

    @Override
    public ImageDTO imageToImageDTO(Image image) {
        if ( image == null ) {
            return null;
        }

        ImageDTO imageDTO = new ImageDTO();

        imageDTO.setId( image.getId() );
        imageDTO.setName( image.getName() );
        imageDTO.setOriginalName( image.getOriginalName() );
        imageDTO.setDescription( image.getDescription() );
        imageDTO.setSize( image.getSize() );
        imageDTO.setContentType( image.getContentType() );
        imageDTO.setSelected( image.getSelected() );

        return imageDTO;
    }

    @Override
    public Image imageDTOToImage(ImageDTO imageDTO) {
        if ( imageDTO == null ) {
            return null;
        }

        Image image = new Image();

        image.setId( imageDTO.getId() );
        image.setName( imageDTO.getName() );
        image.setOriginalName( imageDTO.getOriginalName() );
        image.setDescription( imageDTO.getDescription() );
        image.setSize( imageDTO.getSize() );
        image.setContentType( imageDTO.getContentType() );
        image.setSelected( imageDTO.getSelected() );

        return image;
    }

    @Override
    public List<ImageDTO> imagesToImageDTOs(List<Image> images) {
        if ( images == null ) {
            return null;
        }

        List<ImageDTO> list = new ArrayList<ImageDTO>( images.size() );
        for ( Image image : images ) {
            list.add( imageToImageDTO( image ) );
        }

        return list;
    }

    @Override
    public List<Image> imageDTOsToImages(List<ImageDTO> imageDTOs) {
        if ( imageDTOs == null ) {
            return null;
        }

        List<Image> list = new ArrayList<Image>( imageDTOs.size() );
        for ( ImageDTO imageDTO : imageDTOs ) {
            list.add( imageDTOToImage( imageDTO ) );
        }

        return list;
    }

    @Override
    public void updateDescriptionAndSelectedFromImageDTO(ImageDTO imageDTO, Image image) {
        if ( imageDTO == null ) {
            return;
        }

        image.setId( imageDTO.getId() );
        image.setDescription( imageDTO.getDescription() );
        image.setSelected( imageDTO.getSelected() );
    }

    @Override
    public void imageToImageDTOwithProduct(Image image, ImageDTO imageDTO) {
        if ( image == null ) {
            return;
        }

        imageDTO.setId( image.getId() );
        imageDTO.setName( image.getName() );
        imageDTO.setOriginalName( image.getOriginalName() );
        imageDTO.setDescription( image.getDescription() );
        imageDTO.setSize( image.getSize() );
        imageDTO.setContentType( image.getContentType() );
        imageDTO.setSelected( image.getSelected() );
    }
}
