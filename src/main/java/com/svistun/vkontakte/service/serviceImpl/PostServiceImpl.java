package com.svistun.vkontakte.service.serviceImpl;

import com.svistun.vkontakte.dto.commentDto.request.PostCommentsDto;
import com.svistun.vkontakte.dto.commentDto.response.CommentDto;
import com.svistun.vkontakte.dto.postDto.request.CreatePostDto;
import com.svistun.vkontakte.dto.likeDto.request.LikePostDto;
import com.svistun.vkontakte.dto.postDto.request.PostReviewDto;
import com.svistun.vkontakte.dto.postDto.response.PostReviewDtoResponse;
import com.svistun.vkontakte.dto.userDto.response.UserInfoPostDto;
import com.svistun.vkontakte.entity.Comments;
import com.svistun.vkontakte.entity.LikeMark;
import com.svistun.vkontakte.entity.MediaFile;
import com.svistun.vkontakte.entity.Post;
import com.svistun.vkontakte.repository.CommentRepository;
import com.svistun.vkontakte.repository.LikeRepository;
import com.svistun.vkontakte.repository.MediaFileRepository;
import com.svistun.vkontakte.repository.PostRepository;
import com.svistun.vkontakte.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final MediaFileRepository mediaFileRepository;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;


    public PostServiceImpl(PostRepository postRepository, MediaFileRepository mediaFileRepository, CommentRepository commentRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.mediaFileRepository = mediaFileRepository;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }


    @Transactional
    @Override
    public void createPost(CreatePostDto createPostDto) {
        Post post = new Post();
        MediaFile mediaFile = new MediaFile();
        File file = new File(createPostDto.getPatchMediaFile());

        mediaFile.setUploadDate(LocalDateTime.now()); // date loading file
        mediaFile.setName(file.getName()); //name file
        mediaFile.setHashFile(file.hashCode()); // hash code file
        try {
            mediaFile.setType(Files.probeContentType(file.toPath())); // type file
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaFile.setFileSize((double) file.length()); //file size (bytes)

        String typeFileUser = "";
        if (mediaFile.getType().contains("image")) {
            typeFileUser = "images";
        }
        if (mediaFile.getType().contains("video")) {
            typeFileUser = "video";
        }


        Path serverPath = Paths.get("src/main/resources/files/" + typeFileUser + "/" + createPostDto.getCreatorId());
        if (!Files.exists(serverPath)) {
            File folder = new File("src/main/resources/files/" + typeFileUser + "/" + createPostDto.getCreatorId());
            boolean mkdir = folder.mkdir();
        }

        Path dest = Paths.get(serverPath + "/" + mediaFile.getName());

        if (!new File(mediaFile.getName()).exists()) {
            try {
                copyFileUsingFileStreams(file, dest.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//UUID
        if (mediaFileRepository.getByName(file.getName()) != null &&
                mediaFileRepository.getByName(file.getName()).getHashFile() == file.hashCode()) {
                post.setMedia(mediaFileRepository.getByName(file.getName()));
        } else {
            mediaFile.setPatch(dest.toString());
            mediaFileRepository.save(mediaFile);
            post.setMedia(mediaFile);

        }
        post.setDescription(createPostDto.getDescription());
        post.setCreatorId(createPostDto.getCreatorId());
        post.setDate(LocalDateTime.now());
        postRepository.save(post);


    }

    @Transactional
    @Override
    public List<PostReviewDtoResponse> reviewPost(PostReviewDto postReviewDto) {
        List<Post> posts = postRepository.getPostByCreatorId(postReviewDto.getCreatorId());
        List<PostReviewDtoResponse> responseList = new ArrayList<>();

        for (Post post : posts) {
            PostReviewDtoResponse postReviewDtoResponse = new PostReviewDtoResponse();
            UserInfoPostDto user = new UserInfoPostDto();


            user.setId(post.getCreator().getId());
            user.setFirstName(post.getCreator().getFirstName());
            user.setLastName(post.getCreator().getLastName());


            postReviewDtoResponse.setId(post.getId());
            postReviewDtoResponse.setCreator(user);
            postReviewDtoResponse.setMedia(post.getMedia());
            postReviewDtoResponse.setDescription(post.getDescription());
            postReviewDtoResponse.setDate(post.getDate());


            List<Comments> commentsList = commentRepository.getByPostId(post.getId());
            List<CommentDto> commentDto = new ArrayList<>();
            for (Comments comments : commentsList) {
                CommentDto commentsDto = new CommentDto();
                commentsDto.setAuthorId(comments.getAuthorId());
                commentsDto.setComments(comments.getComment());
                commentsDto.setDateTime(comments.getDate());
                commentDto.add(commentsDto);
            }


            List<LikeMark> likeMarks = likeRepository.getByPostId(post.getId());
            List<LikePostDto> likePostDto = new ArrayList<>();
            for (LikeMark likeMark : likeMarks) {
                LikePostDto likePost = new LikePostDto();
                likePost.setUserId(likeMark.getUserId());
                likePostDto.add(likePost);
            }

            postReviewDtoResponse.setLikes(likePostDto);
            postReviewDtoResponse.setComments(commentDto);
            responseList.add(postReviewDtoResponse);
        }


        return responseList;
    }

    private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        try (InputStream input = new FileInputStream(source); OutputStream output = new FileOutputStream(dest)) {
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        }
    }


}
