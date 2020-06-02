package cmls.spring.msscbeerservice.web.model;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class BeerPagedList extends PageImpl<Beer>{

	public BeerPagedList(List<Beer> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	public BeerPagedList(List<Beer> content) {
		super(content);
	}

}
