import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import GetAxios from "../../utils/axios/GetAxios";
import PostAxios from "../../utils/axios/PostAxios";
import DeleteAxios from "../../utils/axios/DeleteAxios";
import GuestBookEditor from "./GuestBookEditor";

const OtherGuestBook = () => {
    const { userId } = useParams();
    const userIdOnToken = sessionStorage.getItem("userId");

    const [state, setState] = useState([]);
    const [guestBook, setGuestBook] = useState("");

    useEffect(()=>{
        GetAxios('/drawer/' + userId + '/guestbook', {param:{}}, (res) => {
            setState(res.data);
        });
    }, []);

    const handleRegistGuestBook = () => {
        PostAxios('/drawer/' + userId + '/guestbook', {contents: guestBook}, res=>window.location.reload());
    }

    const guestBookInput = 
    <div className="text_box">
        <textarea className="guestbook_comment" value={guestBook} placeholder="새로운 방명록을 작성해보세요. 해당 방명록은 익명으로 작성되며 욕설 및 비방시 무단 삭제 조치 됩니다." 
                            onChange={(e)=>(setGuestBook(e.target.value))}/>
        <br/>
        <div className="common_btn_wrapper">
        <button  className ="common_btn" onClick={handleRegistGuestBook}>등록</button>
        </div>
    </div>

    const edit = (guestBookId) => (
        <div> 
            <GuestBookEditor guestBookId={guestBookId} />
            <button className ="common_btn" onClick={()=>(DeleteAxios('/drawer/' + guestBookId, {}, (res)=>{
                window.location.reload();
            }))}>
                삭제
            </button>
        </div>
    )
    
    return (
        <div className="drawer_wrapper">
            <span className="userid_message">{userId}의 방명록</span>
            <br/>
            <br/>
            <br/>
            {guestBookInput}
            <br/>
            {state.map(guestbook => (
                <div className="text_box" key={guestbook.guestBookId}>
                    <div className="question_box">{guestbook.contents}
                    {guestbook.guestId == userIdOnToken && edit(guestbook.guestBookId)}
                    </div>
                    <br/>
                  <div className="comment_box">  {
                        guestbook.comment.commentId != null ?
                        <div key={guestbook.comment.commentId}>
                            <div className = "guestbook_content"> {guestbook.comment.contents}
                            <span className="guestbook_datetime"> {guestbook.comment.commentCreateDatetime}</span>
                            </div>
                        </div>
                    : <div className = "guestbook_content"> {userId} 님께서 아직 답변을 작성하지 않았어요.</div> } </div>
                    <br/>
                </div>
            ))}

        </div>
    );
}

export default OtherGuestBook;