import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import GetAxios from "../../utils/axios/GetAxios";
import PostAxios from "../../utils/axios/PostAxios";
import DeleteAxios from "../../utils/axios/DeleteAxios";
import CommentEditor from "./CommentEditor";
import MyButton from "../button/MyButton";

const MyGuestBook = () => {
    const { userId } = useParams();
    const [state, setState] = useState([]);
    const [comments, setComments] = useState([]);

    useEffect(()=>{
        GetAxios('/drawer/' + userId + '/guestbook', {param:{}}, (res) => {
            setState(res.data);
        });
    }, []);

    const commentInsertForm = (guestBookId) => (
                    <div>
                        <textarea className="guestbook_comment" value={comments[guestBookId]} placeholder="아직 답변을 작성하지 않았어요." onChange={(e)=>(setComments({...comments, [guestBookId]: e.target.value}))}/>
                        <br/>
                        <div className="common_btn_wrapper">
                        <button className="common_btn" onClick={()=>{
                            PostAxios('/drawer/' + userId + '/guestbook/comment', {guestBookId: guestBookId, contents: comments[guestBookId]}, (res)=>{
                                window.location.reload();
                            });
                        }}>등록</button></div>
                    </div>
    )
    const commentData = (id, contents, datetime) => (
        <div> 
            <div className="guestbook_content"> {contents} </div>
            <CommentEditor commentId={id}/>
            <button className="common_btn"
                onClick={()=>(DeleteAxios('/drawer/' + userId + '/guestbook/comment/' + id, {}, (res)=>{
                    window.location.reload();
                }))}>삭제
            </button>
                <span className="guestbook_datetime"> {datetime} </span>
        </div>
    )

    return (
        <div className="drawer_wrapper">
            <span className="userid_message">{userId}님의 방명록</span>
            <br/>
            <br/>
            <br/>
            {state.map(guestbook => (
                <div className="text_box" key={guestbook.guestBookId}>
                     <div className="question_box">{guestbook.contents}</div>
                    <br />
                    <div className="comment_box">{guestbook.comment.commentId == null ?
                    commentInsertForm(guestbook.guestBookId) :
                    commentData(guestbook.comment.commentId, guestbook.comment.contents, guestbook.comment.commentCreateDatetime)}</div>
                <br/>
                </div>
            ))}
        </div>
    );
}

export default MyGuestBook;