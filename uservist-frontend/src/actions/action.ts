/**
 * A generic axios object that handles data sent via requests and received by reducers.
 * This object must be returned from each action called for reducer.
 * This interface can be extended. Some of the values present here are not available in reducer
 * because they are depending on the result of the request:
 * 1. On SUCCESS:
 *    {
 *      type: 'AWESOME',
 *      payload: { ... },
 *      meta: {
 *        previousAction: { ... }
 *      }
 *    }
 * 2. On ERROR:
 *    {
 *      type:'OH_NO',
 *      error: { ... },
 *      meta: {
 *        previousAction: { ... }
 *      }
 *    }
 * 3. On EXCEPTION:
 *    {
 *      type:'OH_NO',
 *      error: {
 *        status: 0,
 *        ...,
 *      },
 *      meta: {
 *        previousAction: { ... }
 *      }
 *    }
 */
export interface ReducerAction<A> {
  type: string | string[];
  payload?: { client?: string, request: { url?: string } & Object, data?: A } & Object;
  error?: { status?: number } & Object;
  meta?: { previousAction: Object };
}

/**
 * This class represents a type which is passed to reducer action.
 * The action function receives this action and it is later used in reducer to determine the type
 * of request received in axios.
 */
export class ReducerActionTypes {
  public REQUEST: string;
  public SUCCESS: string;
  public FAILURE: string;

  constructor(type: string) {
    this.REQUEST = type + '_REQUEST';
    this.SUCCESS = type + '_SUCCESS';
    this.FAILURE = type + '_FAILURE';
  }

  typesArray() {
    return [this.REQUEST, this.SUCCESS, this.FAILURE];
  }
}