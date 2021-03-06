package ar.edu.unq.desapp.grupoh.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupoh.dto.ReviewDTO;
import ar.edu.unq.desapp.grupoh.model.Review.Review;
import ar.edu.unq.desapp.grupoh.service.ReviewService;
import ar.edu.unq.desapp.grupoh.service.UpdateReviewRequestBody;

@RestController
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	private final String commonPath = "/api/reviews";
	
	@CrossOrigin
    @PostMapping(commonPath)
    public ResponseEntity<Review> createAndSaveNewReview(@RequestBody ReviewDTO requestBody) {
		Review storedReview = this.reviewService.add(requestBody);
		return ResponseEntity.status(HttpStatus.CREATED).body(storedReview);
	}
	
	@CrossOrigin
	@GetMapping(commonPath)
	public ResponseEntity<List<Review>> getReviewsOfContent(
		@RequestParam(value = "contentimdbid") String contentImdbId,
		@RequestParam(value = "pagenumber") Integer pageNumber,
		@RequestParam(value = "pagesize") Integer pageSize,
		@RequestParam(value = "reviewtype", required = false) String reviewType,
		@RequestParam(value = "platformname", required = false) String originPlatformName,
		@RequestParam(value = "spoileralert", required = false) Boolean spoilerAlert,
		@RequestParam(value = "language", required = false) String language,
		@RequestParam(value = "country", required = false) String country,
		@RequestParam(value = "ratingascending", required = false) Boolean ratingAscending,
		@RequestParam(value = "ratingdescending", required = false) Boolean ratingDescending,
		@RequestParam(value = "dateascending", required = false) Boolean dateAscending,
		@RequestParam(value = "datedescending", required = false) Boolean dateDescending,
		@RequestHeader(value = "Api-key") String apiKey
	) {
		return ResponseEntity.ok(
			this.reviewService.get(
				contentImdbId, pageNumber, pageSize, reviewType, originPlatformName, spoilerAlert,
				language, country, ratingAscending, ratingDescending, dateAscending, dateDescending
			)
		);
	}
	
	@CrossOrigin
	@PutMapping(commonPath + "/{id}")
	public ResponseEntity<Review> updateReview(@PathVariable(value = "id") Long id, @RequestBody UpdateReviewRequestBody requestBody) {
		Review updatedReview = this.reviewService.update(id, requestBody);
		return ResponseEntity.ok(updatedReview);
	}
}
