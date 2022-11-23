import { useState } from "react";
import { useParams } from "react-router-dom";
import PutAxios from "../../utils/axios/PutAxios";

const CommentEditor = (props) => {
    const { userId } = useParams();
    const [edit, setEdit] = useState(false);
    const [comment, setComment] = useState("");

    const editForm = <div className="text_box">
                        <input className="input_s" type="text" value={comment} placeholder="수정내용을 입력하세요." onChange={(e)=>(setComment(e.target.value))}/>
                        <button onClick={()=>{
                            PutAxios('/drawer/' + userId + '/guestbook/comment', {commentId: props.commentId, contents: comment}, (res)=>{
                                window.location.reload();
                            });
                        }}>수정</button>
                        <button onClick={(e)=>(setEdit(false))}>수정취소</button>
                    </div>
                    
    const editWord = <button className="common_btn" onClick={(e)=>(setEdit(true))}>수정</button>

    return (
        <>
            {edit ? editForm : editWord}
        </>
    );
}

export default CommentEditor;