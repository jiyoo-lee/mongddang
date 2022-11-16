import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import GetAxios from "../../utils/axios/GetAxios";
import PostAxios from "../../utils/axios/PostAxios";
import DeleteAxios from "../../utils/axios/DeleteAxios";
import CommentEditor from "./CommentEditor";

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
                        <input type="text" value={comments[guestBookId]} placeholder="아직 답변을 작성하지 않았어요." onChange={(e)=>(setComments({...comments, [guestBookId]: e.target.value}))}/>
                        <button onClick={()=>{
                            PostAxios('/drawer/' + userId + '/guestbook/comment', {guestBookId: guestBookId, contents: comments[guestBookId]}, (res)=>{
                                window.location.reload();
                            });
                        }}>등록</button>
                    </div>
    )

    const commentData = (id, contents, datetime) => (
        <div> 
            {contents} {datetime}
            <CommentEditor commentId={id}/>
            
            <div onClick={()=>(DeleteAxios('/drawer/' + userId + '/guestbook/comment/' + id, {}, (res)=>{
                window.location.reload();
            }))}>
                삭제
            </div>
        </div>
    )

    return (
        <>
            나의 방명록
            <br/>
            <br/>
            <br/>
            {state.map(guestbook => (
                <div key={guestbook.guestBookId}>
                    ㅇ {guestbook.contents}
                    <br />
                    {guestbook.comment.commentId == null ?
                     commentInsertForm(guestbook.guestBookId) :
                     commentData(guestbook.comment.commentId, guestbook.comment.contents, guestbook.comment.commentCreateDatetime)}
                <br/>
                </div>
            ))}
        </>
    );
}

export default MyGuestBook;