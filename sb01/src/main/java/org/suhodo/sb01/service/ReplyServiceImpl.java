package org.suhodo.sb01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.suhodo.sb01.domain.Reply;
import org.suhodo.sb01.dto.ReplyDTO;
import org.suhodo.sb01.repository.ReplyRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        return replyRepository.save(reply).getRno();
    }
}
